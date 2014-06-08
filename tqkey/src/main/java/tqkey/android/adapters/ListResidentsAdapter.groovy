package tqkey.android.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import tqkey.android.R
import tqkey.android.entity.User

public class ListResidentsAdapter extends ArrayAdapter<User> {

    private Context mContext

    private static class ViewHolder {
        def profileImageView

        def atHomeTextView

        def updatedAtTextView

        ViewHolder(View view) {
            profileImageView = view.findViewById(R.id.profile_image_view)
            atHomeTextView = view.findViewById(R.id.at_home_text_view)
            updatedAtTextView = (TextView) view.findViewById(R.id.updated_at_text_view)
        }
    }

    public ListResidentsAdapter(Context context) {
        super(context, 0)
        mContext = getContext()
    }

    public void setData(List<User> list) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            addAll(list)
        } else {
            list.each { add(it) }
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder
        if (convertView == null) {
            LayoutInflater mInflater = LayoutInflater.from(getContext())
            convertView = mInflater.inflate(R.layout.show_residents_list_item, parent, false)
            holder = new ViewHolder(convertView)
            convertView.setTag(holder)
        } else {
            holder = (ViewHolder) convertView.getTag()
        }

        User user = getItem(position)
        Picasso.with(mContext)
                .load(mContext.getString(R.string.profile_image_api, user.getName()))
                .resize(120, 120)
                .into(holder.profileImageView as ImageView)
        holder.atHomeTextView.setText(user.getAtHomeText())
        holder.updatedAtTextView.setText(user.getUpdatedAt())
        return convertView
    }

}