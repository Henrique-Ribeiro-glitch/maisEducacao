package junior.henrique.menu.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import junior.henrique.menu.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        buttonRegister.setOnClickListener {
            startActivity<ProjectScreen>()
        }

        buttonList.setOnClickListener {
            startActivity<ListScreen>()
        }

        mainActivityButtonDeslogar.setOnClickListener {
            mAuth?.signOut()
            startActivity<Login>()
            finish()
        }

    }

}