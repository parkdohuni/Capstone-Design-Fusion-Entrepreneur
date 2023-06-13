import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cooperar.data.MatchingData
import com.example.cooperar.databinding.CardMatchingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class MatchingAdapter : RecyclerView.Adapter<MatchingAdapter.MyViewHolder>() {
    var matchList : ArrayList<MatchingData> = arrayListOf()
    /* User Authentication */
    private var auth: FirebaseAuth? = null
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var storage: FirebaseStorage = FirebaseStorage.getInstance()

    init {
        auth = FirebaseAuth.getInstance()

        firestore.collection("match").addSnapshotListener { querySnapshot, _ ->

            // ArrayList 비워줌
            matchList.clear()

            querySnapshot?.let { snapshot ->
                for (document in snapshot.documents) {
                    val item = document.toObject(MatchingData::class.java)
                    item?.let {
                        matchList.add(it)
                    }
                }
            }
            Log.e("match match match", matchList.toString())
            notifyDataSetChanged()
        }
    }
    class MyViewHolder(private val binding: CardMatchingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var itemTitle: TextView = binding.matchingTitleTv
        var itemAddress: TextView = binding.matchingAddressTv
    }

    // 1. Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            CardMatchingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    // 2. Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val match: MatchingData = matchList[position]
        holder.itemTitle.text = match.title
        holder.itemAddress.text = match.address
    }

    // 3. Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return matchList.size
    }
}
