package com.example.justimagine;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ContentResolver;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.justimagine.databinding.FragmentGameBaseBinding;

import java.time.Duration;

public class GameBaseFragment extends Fragment {
    FragmentGameBaseBinding mBinding;

    public GameBaseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentGameBaseBinding.inflate(getLayoutInflater());
        View v = mBinding.getRoot();

        View.OnTouchListener onTouchListener  = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String clipText = "moving card";
                ClipData.Item item = new ClipData.Item(clipText);
                String mimeType[] =  {ClipDescription.MIMETYPE_TEXT_PLAIN};
                ClipData data = new ClipData(clipText,mimeType,item);

                View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
                v.startDragAndDrop(data,dragShadowBuilder,v,0);
                v.setVisibility(View.INVISIBLE);
                return true;
            }
        };

        View.OnDragListener dragListener = new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()){
                    case DragEvent.ACTION_DRAG_STARTED:
                        return  event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN);
                    case DragEvent.ACTION_DRAG_ENTERED:
                    case DragEvent.ACTION_DRAG_EXITED:
                    case DragEvent.ACTION_DRAG_ENDED:
                        v.invalidate();
                        return true;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        return true;
                    case DragEvent.ACTION_DROP:
                        ClipData.Item item = event.getClipData().getItemAt(0);
                        CharSequence dragData = item.getText();


                        View v2 = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) container.getParent();
                        owner.removeView(v2);
                        FrameLayout destination = (FrameLayout) v;

                        destination.addView(v2);
                        v2.setVisibility(View.VISIBLE);
                        //v.invalidate();
                        return true;
                    default:
                        return false;
                }
            }
        };

        mBinding.card.setOnTouchListener(onTouchListener);

        mBinding.gameTop0.setOnDragListener(dragListener);
        mBinding.gameTop1.setOnDragListener(dragListener);
        mBinding.gameTop2.setOnDragListener(dragListener);
        mBinding.gameTop3.setOnDragListener(dragListener);
        mBinding.gameTop4.setOnDragListener(dragListener);
        mBinding.gameTop5.setOnDragListener(dragListener);

        mBinding.gameBottom0.setOnDragListener(dragListener);
        mBinding.gameBottom0.setOnDragListener(dragListener);
        mBinding.gameBottom0.setOnDragListener(dragListener);
        mBinding.gameBottom0.setOnDragListener(dragListener);
        mBinding.gameBottom0.setOnDragListener(dragListener);
        mBinding.gameBottom0.setOnDragListener(dragListener);

        return v;
    }
}
