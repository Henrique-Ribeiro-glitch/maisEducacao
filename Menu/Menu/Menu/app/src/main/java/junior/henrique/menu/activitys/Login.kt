package junior.henrique.menu.activitys
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import junior.henrique.menu.R
import junior.henrique.menu.modelo.Usuario

import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class Login : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var mAuthStateListener: FirebaseAuth.AuthStateListener? = null
    private var mUser: FirebaseUser? = null

    override fun onStart() {
        super.onStart()
        mAuth?.addAuthStateListener(mAuthStateListener!!)
    }

    override fun onStop() {
        super.onStop()
        if(mAuthStateListener != null) {
            mAuth?.removeAuthStateListener(mAuthStateListener!!)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        FirebaseApp.initializeApp(this)
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth?.currentUser
        telaLoginProgress.visibility = View.INVISIBLE

        mAuthStateListener = FirebaseAuth.AuthStateListener {
            if(mUser != null) {
                startActivity<MainActivity>()
                finish()
            }
        }

        telaLoginButtonEntrar.setOnClickListener {

            if(telaLoginEditTextLogin.text.toString().isEmpty()
                || telaLoginEditTextSenha.text.toString().isEmpty()) {
                toast(getString(R.string.DigiteUsuarioEOuSenha))
                return@setOnClickListener
            }

            var usuario = Usuario()

            usuario.login = telaLoginEditTextLogin.text.toString()
            usuario.senha = telaLoginEditTextSenha.text.toString()
            telaLoginProgress.setVisibility(View.VISIBLE)

            mAuth?.signInWithEmailAndPassword(usuario.login.toString(),
                usuario.senha.toString())?.addOnCompleteListener(object:
                OnCompleteListener<AuthResult>{

                override fun onComplete(p0: Task<AuthResult>) {
                    telaLoginProgress.visibility = View.INVISIBLE

                    if(!p0.isSuccessful) {
                        toast(getString(R.string.UsuarioSenhaInvalidos))
                        return
                    }

                    toast(getString(R.string.LoginEfetuado))
                    startActivity<MainActivity>()
                    finish()
                }

            })
        }

        telaLoginButtonCadastrarUsuario.setOnClickListener {
            if(telaLoginEditTextLogin.text.toString().isEmpty()
                || telaLoginEditTextSenha.text.toString().isEmpty()) {
                toast(getString(R.string.UsuarioEOuSenha2))
                return@setOnClickListener
            }

            var usuario = Usuario()
            usuario.login = telaLoginEditTextLogin.text.toString()
            usuario.senha = telaLoginEditTextSenha.text.toString()
            telaLoginProgress.setVisibility(View.VISIBLE)

            mAuth?.createUserWithEmailAndPassword(usuario.login.toString(),
                usuario.senha.toString())?.addOnCompleteListener(object:
                OnCompleteListener<AuthResult>{

                override fun onComplete(p0: Task<AuthResult>) {
                    telaLoginProgress.visibility = View.INVISIBLE

                    if(!p0.isSuccessful) {
                        toast(getString(R.string.CriarUsuário))
                        return
                    }

                    toast(getString(R.string.UsuarioCriado))
                    startActivity<MainActivity>()
                    finish()
                }
            })
        }

        telaLoginButtonEsqueciASenha.setOnClickListener {
            if(telaLoginEditTextLogin.text.toString().isEmpty()) {
                toast(getString(R.string.DigiteOSeuEmail))
                return@setOnClickListener
            }

            mAuth?.sendPasswordResetEmail(telaLoginEditTextLogin.text.toString())
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        toast(getString(R.string.RedefinicaoDeSenha))
                    }
                }
        }
    }

}