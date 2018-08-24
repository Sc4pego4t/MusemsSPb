package com.nixbyte.project.activities.map;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.nixbyte.project.R;
import com.nixbyte.project.modules.activity.AbstractActivity;
import com.nixbyte.project.utils.ApiFactory;
import com.nixbyte.project.utils.Keywords;
import com.nixbyte.project.utils.creatingObservers.CreatingObservers;


/**
 * Created by scapegoat on 09/04/2018.
 */

public class MarkerFragment extends Fragment implements View.OnClickListener {

    Button like,dislike;
    String markerId;
    EditText review;
    TextView show;

    MapView mView;
    MapActivity mActivity;

    RecyclerView list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.marker_info, container, false);
        TextView title = view.findViewById(R.id.title);

        TextView text = view.findViewById(R.id.text);
        TextView address = view.findViewById(R.id.address);
        TextView website = view.findViewById(R.id.website);
        TextView textT = view.findViewById(R.id.textT);
        TextView addressT = view.findViewById(R.id.addressT);
        TextView websiteT = view.findViewById(R.id.webT);

        TextView accept = view.findViewById(R.id.accept);
        review = view.findViewById(R.id.review);
        show = view.findViewById(R.id.show);
        list = view.findViewById(R.id.reviewsList);
        TextView rate = view.findViewById(R.id.rate);

        mView = ((MapActivity) getContext()).getView();
        mActivity = (MapActivity) getActivity();

        list.setLayoutManager(new LinearLayoutManager(mActivity));


//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(priceTextView.getText()+phrase);
        like = view.findViewById(R.id.like);
        dislike = view.findViewById(R.id.dislike);

        accept.setOnClickListener(this);
        like.setOnClickListener(this);
        dislike.setOnClickListener(this);
        show.setOnClickListener(this);

        mView.like = like;
        mView.dislike = dislike;
        mView.rate = rate;

        String[] info = mView.mr.getSnippet().split(Keywords.divider);
        int i = 0;
        for (String s : info) {
            String[] couple = s.split(Keywords.coupleDivider);
            boolean temp = false;
            if (couple[0].equals("1"))
                temp = true;
            if (temp) {
                switch (i) {
                    case 0:
                        text.setText(s);
                        text.setVisibility(View.VISIBLE);
                        textT.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        address.setText(s);
                        addressT.setVisibility(View.VISIBLE);
                        address.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        website.setText(Html.
                                fromHtml("<a href=" + s + "><font color=#000000>" + s + "</font></a>"));
                        websiteT.setVisibility(View.VISIBLE);
                        website.setVisibility(View.VISIBLE);

                        break;
                }
                i++;
            }
        }

        title.setText(mView.mr.getTitle());


        markerId = mView.mr.getId();

        ApiFactory.getReviewsAndRate(markerId, mActivity.autorized).subscribe(
                CreatingObservers.arrayObserver(
                        mView,
                        null,
                        "Ошибка",
                        false,
                        list
                )
        );

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.like:
                likeOrDislike("1");
                Log.e("GG","LIKE");
                break;
            case R.id.dislike:
                likeOrDislike("0");
                Log.e("GG","DISLIKE");
                break;
            case R.id.accept:
                if(!((MapActivity)getActivity()).autorized.equals("")){
                    ApiFactory.addReview(review.getText().toString(),mActivity.autorized,markerId).subscribe(
                            CreatingObservers.rateObserver(
                                    mActivity,
                                    mView,
                                    "Отзыв успешно добавлен",
                                    "Произошла ошибка при добавлении отзыва",
                                    true
                            )
                    );
                } else {
                    showAlertDialogAboutAutorization("оставить комментарий");
                }
                break;
            case R.id.show:
                if(list.getVisibility()==View.GONE) {
                    show.setText(getText(R.string.s22));
                    list.setVisibility(View.VISIBLE);
                } else {
                    show.setText(getText(R.string.s21));
                    list.setVisibility(View.GONE);
                }
                break;

        }
    }

    public void likeOrDislike(String like){

        if(mActivity.autorized.equals("")){
            showAlertDialogAboutAutorization("поставить рейтинг");
            return;
        }
        mView.likeOrDis=like;
        Log.e("GG","yra");
        ApiFactory.setRate(like,mActivity.autorized,markerId).subscribe(
                CreatingObservers.rateObserver(
                        mActivity,
                        mView,
                        null,
                        "Ошибка при добавлении отзыва",
                        false
                )
        );
    }

    public void showAlertDialogAboutAutorization(String needs){
        AlertDialog.Builder builder=new AlertDialog.Builder(mActivity,AbstractActivity.getStyle());
        builder.setPositiveButton(R.string.ok, (di, i) -> { });
        builder.setTitle(R.string.auth);
        builder.setMessage("Для того чтобы " +needs+" авторизуйтесь");
        builder.show();
    }
}
