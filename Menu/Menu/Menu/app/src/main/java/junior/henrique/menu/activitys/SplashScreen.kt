package junior.henrique.menu.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import junior.henrique.menu.R
import org.jetbrains.anko.startActivity

class SplashScreen : AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 4000

    internal val mRunnable = Runnable  {
        if(!isFinishing) {
            startActivity<Login>()
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        mDelayHandler= Handler()
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
    }

    override fun onDestroy() {
        super.onDestroy()
        if(mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
    }
}
