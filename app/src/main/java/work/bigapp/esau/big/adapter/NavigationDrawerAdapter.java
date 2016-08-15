package work.bigapp.esau.big.adapter;

/**
 * Created by abdisalam on 7/22/16.
 */

public class NavigationDrawerAdapter extends android.support.v7.widget.RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {
    java.util.List<work.bigapp.esau.big.model.NavDrawerItem> data = java.util.Collections.emptyList();
    private android.view.LayoutInflater inflater;
    private android.content.Context context;

    public NavigationDrawerAdapter(android.content.Context context, java.util.List<work.bigapp.esau.big.model.NavDrawerItem> data) {
        this.context = context;
        inflater = android.view.LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(android.view.ViewGroup parent, int viewType) {
        android.view.View view = inflater.inflate(work.bigapp.esau.big.R.layout.nav_drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        work.bigapp.esau.big.model.NavDrawerItem current = data.get(position);
        holder.title.setText(current.getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        android.widget.TextView title;

        public MyViewHolder(android.view.View itemView) {
            super(itemView);
            title = (android.widget.TextView) itemView.findViewById(work.bigapp.esau.big.R.id.title);
        }
    }
}