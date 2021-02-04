package ir.mnemati.baseconvertor

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {

    private lateinit var media: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        media = MediaPlayer.create(this, R.raw.splashsound)
        media.start()

        val animationLeft = AnimationUtils.loadAnimation(this, R.anim.move_left)
        val animationRight = AnimationUtils.loadAnimation(this, R.anim.move_right)

        val profile_image: CircleImageView = findViewById(R.id.profile_image)
        val tv_title1: TextView = findViewById(R.id.sub_title1)
        val tv_title2: TextView = findViewById(R.id.sub_title2)
        val tv_title3: TextView = findViewById(R.id.sub_title3)

        profile_image.animation = animationLeft
        tv_title1.animation = animationLeft
        tv_title2.animation = animationRight
        tv_title3.animation = animationRight

        Handler().postDelayed({
            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
            finish()
        }, 6000)

    }

    override fun onStop() {
        media.stop()
        super.onStop()
    }
}