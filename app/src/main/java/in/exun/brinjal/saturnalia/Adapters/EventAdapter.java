package in.exun.brinjal.saturnalia.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import in.exun.brinjal.saturnalia.R;
import in.exun.brinjal.saturnalia.helper.EventName;

public class EventAdapter extends CursorRecyclerViewAdapter<EventAdapter.ViewHolder>{

    private static final String TAG = "EventAdapter";
    private static MyClickListener myClickListener;
    private Context context;

    public EventAdapter(Context context, Cursor cursor){
        super(context,cursor);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_event, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        final EventName subcatList = new EventName(cursor);
        viewHolder.mTextView.setText(subcatList.getTitle());
        String image = subcatList.getThumb();
        //String header_image = subcatList.getHeader_image();

        //int headerID =context.getResources().getIdentifier(header_image, "drawable", context.getPackageName());
        //viewHolder.mhTabView.setImageResource(headerID);

        //Log.d(TAG, "onBindViewHolder: " + header_image + " " +headerID );

        int resID;
        resID = context.getResources().getIdentifier(image, "drawable", context.getPackageName());
//        Log.d(TAG, "onBindViewHolder: " + image + " " + resID);

        viewHolder.mImageView.setImageDrawable(context.getResources().getDrawable(resID));

        viewHolder.mCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickListener.onItemClick(subcatList.getId(),v);
            }
        });

    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        EventAdapter.myClickListener = myClickListener;
    }

    public interface MyClickListener {
        void onItemClick(int id, View v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;
        public CardView mCV;
        public ImageView mhTabView;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.name);
            mImageView =(ImageView)view.findViewById(R.id.event_icon);
            mCV = (CardView) view.findViewById(R.id.cv);
            mhTabView=(ImageView)view.findViewById(R.id.htab_header);
        }
    }
}
