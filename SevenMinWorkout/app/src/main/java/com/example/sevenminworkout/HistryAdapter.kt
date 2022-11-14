import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sevenminworkout.databinding.ActivityHistryBinding
import com.example.sevenminworkout.databinding.ItemHistoryRowBinding

class HistryAdapter(private val items:ArrayList<String>):
    RecyclerView.Adapter<HistryAdapter.ViewHolder>() {
    class ViewHolder(binding: ItemHistoryRowBinding):RecyclerView.ViewHolder(binding.root){
        val llHistryItemMain=binding.llHistryItemMain
        val tvItem=binding.tvItem
        val tvPosition=binding.tvPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistoryRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date= items[position]
        holder.tvPosition.text=(position+1).toString()
        holder.tvItem.text=date

        if(position%2==0){
            holder.llHistryItemMain.setBackgroundColor(
                Color.parseColor("#EBEBEB")
            )
        }else{
            holder.llHistryItemMain.setBackgroundColor(
                Color.parseColor("#FFFFFF")
            )
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}