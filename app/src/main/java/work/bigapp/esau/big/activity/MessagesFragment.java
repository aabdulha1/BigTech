package work.bigapp.esau.big.activity;

/**
 * Created by abdisalam on 7/22/16.
 */
public class MessagesFragment extends android.support.v4.app.Fragment {

    public MessagesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public android.view.View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container,
                             android.os.Bundle savedInstanceState) {
        android.view.View rootView = inflater.inflate(work.bigapp.esau.big.R.layout.fragment_messages, container, false);


        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(android.app.Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
