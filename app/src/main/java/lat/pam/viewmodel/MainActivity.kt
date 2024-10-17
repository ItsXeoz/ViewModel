package lat.pam.viewmodel

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class MainActivity : AppCompatActivity() {
    // ViewModel property
    private val model: NameViewModel by viewModels()

    // Counter variable
    var mCount = 0

    // Views
    private lateinit var buttonCountUp: Button
    private lateinit var mShowCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Find views after layout is set
        buttonCountUp = findViewById(R.id.button_count)
        mShowCount = findViewById(R.id.show_count)

        // Set up button click listener to update ViewModel's LiveData
        buttonCountUp.setOnClickListener {
            mCount += 1
            model.currentName.value = mCount
        }

        // Observer to update the UI when LiveData changes
        val nameObserver = Observer<Int> { newName ->
            mShowCount.text = newName.toString()
        }

        // Observe LiveData from the ViewModel
        model.currentName.observe(this, nameObserver)
    }
}

class NameViewModel : ViewModel() {
    // LiveData to hold the count value
    val currentName: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply { value = 0 } // Set default value to 0
    }
}
