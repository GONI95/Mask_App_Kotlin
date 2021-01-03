package com.example.mask_app_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mask_app_kotlin.model.Store


class StoreAdapter(mainActivity: MainActivity) : RecyclerView.Adapter<StoreAdapter.StoreViewHolder?>() {

    private var mItems: List<Store> = ArrayList()
    var context: Context = mainActivity

    fun updateItems(items: List<Store>) {
        mItems = items
        notifyDataSetChanged() //UI 갱신
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_store, parent, false)

        return StoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val store = mItems[position]

        holder.name.text = store.name
        holder.address.text = store.addr
        //holder.distance!!.text = Math.round(store.getDistance_unit() * 100) / 100.0.toString() + "km"

        var remainStat = "마스크 재고 수준"
        var mask_count = "마스크 재고 수량"
        var color: Int // 재고 수준 색상으로 가시화

        when(store.remain_stat){
            "plenty" -> {
                remainStat = "충분";
                mask_count = "100개 이상";
                color = ContextCompat.getColor(context, R.color._plenty);
            }
            "some" -> {
                remainStat = "여유";
                mask_count = "30개 이상";
                color = ContextCompat.getColor(context, R.color._some);
            }
            "few" -> {
                remainStat = "매진 임박";
                mask_count = "2개 이상";
                color = ContextCompat.getColor(context, R.color._few);
            }
            "empty" -> {
                remainStat = "재고 부족";
                mask_count = "1개 이하";
                color = ContextCompat.getColor(context, R.color._empty);
            }
            "break" -> {
                remainStat = "재고 없음";
                mask_count = "판매 중지";
                color = ContextCompat.getColor(context, R.color._break);
            }
            else -> {
                remainStat = "재고 수준 오류";
                mask_count = "재고 수량 오류";
                color = ContextCompat.getColor(context, R.color._error);
            }
        }
        holder.remain.setText(remainStat);
        holder.remain.setTextColor(color);
        // @SuppressLint 추가해줘야함 (color 인식을 못함)
        holder.count.setText(mask_count);
    }

    override fun getItemCount(): Int {
        return mItems.size;
    }

    class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name: TextView = itemView.findViewById(R.id.name_textView)
        var address: TextView = itemView.findViewById(R.id.addr_textView)
        var remain: TextView = itemView.findViewById(R.id.remain_textView)
        var count: TextView = itemView.findViewById(R.id.count_textView)
        var distance: TextView = itemView.findViewById(R.id.distance_textView)
    }
}