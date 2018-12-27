package gewalin.th.ac.rmutl.physical;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    private VideoView videoView;
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Play Video
        playVideo();

//        Start Video
        startVideo();



    }  // Main Method

    private void startVideo() {
        Button button = getView().findViewById(R.id.btnStart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();
            }
        });
    }

    private void playVideo() {
        videoView  = getView().findViewById(R.id.myVideoView);
        videoView.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName()+ "/" + R.raw.vedio1));
        videoView.setMediaController(new MediaController(getActivity()));
        videoView.requestFocus();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

}
