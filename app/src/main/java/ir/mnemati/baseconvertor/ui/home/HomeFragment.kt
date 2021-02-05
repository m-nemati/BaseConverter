package ir.mnemati.baseconvertor.ui.home

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ir.mnemati.baseconvertor.R
import java.lang.Exception
import java.lang.Math.pow

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    //var convert_type: Int? = null
    var convert_type: Int = 0
    var input_data: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val et_input: EditText = root.findViewById(R.id.et_input)
        val tv_result:TextView = root.findViewById(R.id.tv_result_Content)
        val btn_convert:Button = root.findViewById(R.id.btn_convert)
        val btn_copy:Button = root.findViewById(R.id.btn_copy)
        val btn_clear:Button = root.findViewById(R.id.btn_clear)
        val rg_select:RadioGroup = root.findViewById(R.id.rg_convert_select)
        val rb_10to2:RadioButton = root.findViewById(R.id.rb_10to2)
        val rb_10to16:RadioButton = root.findViewById(R.id.rb_10to16)
        val rb_2to10:RadioButton = root.findViewById(R.id.rb_2to10)
        val rb_2to16:RadioButton = root.findViewById(R.id.rb_2to16)

        fun convert10to2(input_get: String){
            var input: Int = 0

            try {
                 input = input_get.toInt()
            }
            catch (ex:Exception){
                Toast.makeText(activity, "Inserted input not correct!", Toast.LENGTH_SHORT).show()
            }
            var reminder: Int = 0
            var output_calc: String = ""

            while (input != 0){

                reminder = input % 2
                input /=  2
                output_calc = reminder.toString() + output_calc
            }

            tv_result.text = output_calc

        }
        fun convert10to16(){}
        fun convert2to10(input_get:String){
            var input = input_get.reversed()
            var sum = 0

            for (i in 0..input.length - 1) {
                sum += input[i].toString().toInt() * pow(2.toDouble(), i.toDouble()).toInt()

            }
            tv_result.text = sum.toString()

        }
        fun convert2to16(){}
        fun convert0(){
            Toast.makeText(activity, "Please select convert type", Toast.LENGTH_SHORT).show()
        }

        rg_select.setOnCheckedChangeListener { group, checkedId ->

            convert_type = when(checkedId) {
                R.id.rb_10to2 -> 1
                R.id.rb_10to16 -> 2
                R.id.rb_2to10 -> 3
                R.id.rb_2to16-> 4
                else -> 0
            }
        }
        btn_convert.setOnClickListener {

            if (et_input.text.isEmpty()) {
                Toast.makeText(activity, "Please Enter Input string", Toast.LENGTH_SHORT).show()
            }
            else {
                input_data = et_input.text.toString()
                when(convert_type) {

                    1 -> convert10to2(input_data)
                    2 -> convert10to16()
                    3 -> convert2to10(input_data)
                    4 -> convert2to16()
                    else -> convert0()
                }
            }
        }

        btn_clear.setOnClickListener {
            et_input.text.clear()
            tv_result.text = ""
            et_input.requestFocus()
            Toast.makeText(activity, "Clear", Toast.LENGTH_SHORT).show()
        }

        btn_copy.setOnClickListener {

            (requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager).apply {
                setPrimaryClip(ClipData.newPlainText("simple text", tv_result.text))
            }
            Toast.makeText(activity, "Copy Done", Toast.LENGTH_SHORT).show()
        }

        return root
    }
}