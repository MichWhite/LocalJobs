package com.example.localjobs
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.layout_workers.*
private var ctx: Context? = null
private var self: View? = null

class WorkersFragment : Fragment() {
override fun onCreateView(inflater: LayoutInflater,
                          container: ViewGroup?,
                          savedInstanceState: Bundle?): View? {
    ctx = container?.context
    self = LayoutInflater.from(ctx).inflate(R.layout.layout_workers, container, false)
    val bDaButton = self?.findViewById<androidx.cardview.widget.CardView>(R.id.Barber)
    bDaButton?.setOnClickListener {
        Toast.makeText(ctx, "button works!", Toast.LENGTH_SHORT).show()

    }
    return self

}
}