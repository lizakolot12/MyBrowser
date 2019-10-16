package ua.kolot.filebrowser.ui.editresource

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ua.kolot.filebrowser.R

class EditResourceActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_resource_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, EditResourceFragment.newInstance(intent.getIntExtra("id",0)))
                .commitNow()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
