package iwh.empowher;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseAdapter
{
    Context context;
    List<S_Events> valueList;
    public ListAdapter(List<S_Events> listValue, Context context)
    {
        this.context = context;
        this.valueList = listValue;
    }

    @Override
    public int getCount()
    {
        return this.valueList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.valueList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewItem viewItem = null;
        if(convertView == null)
        {
            viewItem = new ViewItem();
            LayoutInflater layoutInfiater = (LayoutInflater)this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            //LayoutInflater layoutInfiater = LayoutInflater.from(context);
            if(position % 4 == 0)
            convertView = layoutInfiater.inflate(R.layout.list_adapter_view1, null);
            else if(position % 4 ==1)
                convertView = layoutInfiater.inflate(R.layout.list_adapter_view2, null);
            else if (position % 4 == 2)
                convertView = layoutInfiater.inflate(R.layout.list_adapter_view3, null);
            else if (position % 4 == 3)
                convertView = layoutInfiater.inflate(R.layout.list_adapter_view4, null);

            viewItem.txtTitle = (TextView)convertView.findViewById(R.id.adapter_text_title);
            viewItem.txtDescription = (TextView)convertView.findViewById(R.id.adapter_text_description);
            convertView.setTag(viewItem);
        }
        else
        {
            viewItem = (ViewItem) convertView.getTag();
        }

        viewItem.txtTitle.setText(valueList.get(position).s_title);
        viewItem.txtDescription.setText(valueList.get(position).s_desc);

        return convertView;
    }
}

class ViewItem
{

    TextView txtTitle;
    TextView txtDescription;
}

