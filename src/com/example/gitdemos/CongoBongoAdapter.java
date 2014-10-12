package sk.ursus.congobongo;

import sk.ursus.congobongo.R;
import java.text.ParseException;
import java.util.Date;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sk.ursus.congobongo.provider.JokesContract.CongoBongo;
import sk.ursus.congobongo.util.Utils;

public class CongoBongoAdapter extends CursorAdapter {

	private LayoutInflater mInflater;

	public CongoBongoAdapter(Context context) {
		super(context, null, 0);
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		final ViewHolder holder = (ViewHolder) view.getTag();

		final int position = cursor.getPosition();
		switch (position % 5) {
			case 0:
				view.setBackgroundColor(0xFF424242);
				holder.title
						.setText("První žena na Mìsíci:\nHoustone, máme problém!\n- Co?\nAle nic, nech to být.\n- Co se stalo?\nNic...\n- Tak mi to øekni.\nNe.");
				break;
			case 1:
				view.setBackgroundColor(0xFFFF8000);
				holder.title
						.setText("Kúpia Rusi od Nemcov motorové píly, ktoré vraj napília za hodinu 10 kubíkov dreva.\nPo èase Rusi zavolajú Nemca, lebo nikdy nenapília viac ako 8 kubíkov, aby im to predviedol.\nNemec naštartuje pílu a reže. Prvý, druhý, tretí až desiaty kubík.\nKeï skonèí Hovorí:\n- Tak vidíte že to ide.\nRusi nechápavo:\n- To sa aj ŠTARTUJE?");
				break;
			case 2:
				view.setBackgroundColor(0xFFB40404);
				break;
			case 3:
				view.setBackgroundColor(0xFF5F04B4);
				break;
			case 4:
				view.setBackgroundColor(0xFF2E64FE);
				break;
		}

		// Title
		// String title =
		// cursor.getString(cursor.getColumnIndex(CongoBongo.COLUMN_TITLE));
		// holder.title.setText(title);

		// Timestamp
		// String timestamp =
		// cursor.getString(cursor.getColumnIndex(CongoBongo.COLUMN_CREATED_AT));
		// holder.timestamp.setText(parseTimestamp(timestamp));
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		View view = mInflater.inflate(R.layout.item_list, parent, false);
		ViewHolder holder = new ViewHolder();

		holder.title = (TextView) view.findViewById(R.id.titleTextView);
		// holder.timestamp = (TextView)
		// view.findViewById(R.id.timestampTextView);

		view.setTag(holder);
		return view;
	}

	private String parseTimestamp(String timestamp) {
		try {
			Date dateAdded = Utils.DATE_PARSER.parse(timestamp);
			// + 1000 because when refreshed right away
			// it says "0 seconds ago"
			CharSequence charSeqAdded = DateUtils.getRelativeTimeSpanString(
					dateAdded.getTime(),
					System.currentTimeMillis() + 1000,
					DateUtils.SECOND_IN_MILLIS
					);

			return charSeqAdded.toString();

		} catch (ParseException e) {
		}
		return timestamp;
	}

	static class ViewHolder {
		public TextView title;
		public TextView timestamp;
	}

}
