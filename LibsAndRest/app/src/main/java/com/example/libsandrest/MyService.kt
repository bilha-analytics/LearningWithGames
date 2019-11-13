package com.example.libsandrest

import android.annotation.TargetApi
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.util.concurrent.locks.ReentrantLock

class MyService : Service() {

    //manage queue of requests b/c otherwise on same thread as calling UI app
    private val lock = ReentrantLock()
//    private val requestsQueue = ArrayList<Thread>() // Create a thread per request
    private val requestsQueue = ArrayList<Intent>() // queue requests and run in same thread
//    private lateinit  var doThread: Thread

    var threadIsRunning = false

    private lateinit var doThread : Thread

    private var counter = 0

    inner  class ServiceRequestObject {
        lateinit var doIntent: Intent

        fun ServiceRequestObject(doIntent: Intent){
            this.doIntent = doIntent
        }

    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
        //return 1 //null //THIS METHOD MUST BE IMPLEMENTED EVEN IF NOT IN USE; so return null as hack
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        super.onStartCommand(intent, flags, startId)

        if( /*doThread != null &&*/ !threadIsRunning){
            Log.d("myservice", "Creating the doProcess thread b/c is not running ")
            doThread = Thread{
                while ( threadIsRunning ) {
                    while (requestsQueue.size > 0) {
                        lock.lock()
                        val gIntent = requestsQueue.removeAt(0)
                        lock.unlock()

                        if (gIntent != null) {
                            processRequest(gIntent)

                            lock.lock()
                            counter++
                            Log.d("myservice", "this thread incremented counter to: $counter")
                            lock.unlock()
                        }
                    }
                    //TODO: wait fo lock.notify else busy waiting !!
                }
            }

            threadIsRunning = true
            doThread.start()

            // This is necessary for later versions else will not show on the notification  bar
            val mgr : NotificationManager = getSystemService( Context.NOTIFICATION_SERVICE ) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
                val channel = NotificationChannel( "123", "123", NotificationManager.IMPORTANCE_DEFAULT)

                mgr.createNotificationChannel( channel )
            }

            //Setup notification that we're running and start main screen if they click on the notification
            val notification = Notification.Builder(this, "123")
            notification.setContentTitle( "MyServiceApp" )
            notification.setContentText( "The service is running!!!" )
            notification.setSmallIcon( android.R.drawable.btn_plus )

            val notificationIntent = Intent( this@MyService, MainActivity::class.java )
            val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
            notification.setContentIntent( pendingIntent )

            mgr.notify(123, notification.build())

        }

        if( intent != null){
            Log.d("myservice", "Adding a new quests: before size = ${requestsQueue.size }")

            // Opt 2"; use same thread and a queue
            lock.lock()
            requestsQueue.add(  intent)
            lock.unlock()


            //Opt 1:  creates a new thread per request
//
//            val thread = Thread{
//                processRequest( intent )
//                lock.lock()
//                counter++
//                Log.d("myservice", "this thread incremented counter to: $counter")
//                lock.unlock()
//            }
//
////            lock.wait()
////            lock.lock()
//            requestsQueue.add( thread )
////            lock.notifyAll()
////            lock.unlock()

            Log.d("myservice", "New request added.. after size = ${requestsQueue.size }")
//
//            thread.start()
        }


        return START_STICKY // SERVICE CONTINUES TO LEARN AFTER TASK COMPLETION
    }

    fun processRequest(intent: Intent){

        val name = intent.getStringExtra("name")
        val value = intent.getIntExtra("value", -99)

        Thread.sleep( 5000 )

        Log.d("myservice", "The name = $name and the value = $value")

        // TODO: this part b/c context item
//        Toast.makeText(this@MyService, "name = '$name', \nvalue = '$value'", Toast.LENGTH_LONG ).show()

        // trigger back ui app
        val done = Intent()
        done.action = "downloadComplete"
        done.putExtra("fileLocation", "bla bla bla")
        sendBroadcast( done )
    }
}
