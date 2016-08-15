package work.bigapp.esau.big.activity;

/**
 * Created by abdisalam on 7/22/16.
 */


public class FragmentDrawer extends android.support.v4.app.Fragment {

    private static String TAG = FragmentDrawer.class.getSimpleName();

    private android.support.v7.widget.RecyclerView recyclerView;
    private android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    private android.support.v4.widget.DrawerLayout mDrawerLayout;
    private work.bigapp.esau.big.adapter.NavigationDrawerAdapter adapter;
    private android.view.View containerView;
    private static String[] titles = null;
    private FragmentDrawerListener drawerListener;

    public FragmentDrawer() {

    }

    public void setDrawerListener(FragmentDrawerListener listener) {
        this.drawerListener = listener;
    }

    public static java.util.List<work.bigapp.esau.big.model.NavDrawerItem> getData() {
        java.util.List<work.bigapp.esau.big.model.NavDrawerItem> data = new java.util.ArrayList<>();


        // preparing navigation drawer items
        for (int i = 0; i < titles.length; i++) {
            work.bigapp.esau.big.model.NavDrawerItem navItem = new work.bigapp.esau.big.model.NavDrawerItem();
            navItem.setTitle(titles[i]);
            data.add(navItem);
        }
        return data;
    }

    @Override
    public void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // drawer labels
        titles = getActivity().getResources().getStringArray(work.bigapp.esau.big.R.array.nav_drawer_labels);
    }

    @Override
    public android.view.View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container,
                             android.os.Bundle savedInstanceState) {
        // Inflating view layout
        android.view.View layout = inflater.inflate(work.bigapp.esau.big.R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (android.support.v7.widget.RecyclerView) layout.findViewById(work.bigapp.esau.big.R.id.drawerList);

        adapter = new work.bigapp.esau.big.adapter.NavigationDrawerAdapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new android.support.v7.widget.LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(android.view.View view, int position) {
                drawerListener.onDrawerItemSelected(view, position);
                mDrawerLayout.closeDrawer(containerView);
            }

            @Override
            public void onLongClick(android.view.View view, int position) {

            }
        }));

        return layout;
    }


    public void setUp(int fragmentId, android.support.v4.widget.DrawerLayout drawerLayout, final android.support.v7.widget.Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, work.bigapp.esau.big.R.string.drawer_open, work.bigapp.esau.big.R.string.drawer_close) {
            @Override
            public void onDrawerOpened(android.view.View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(android.view.View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(android.view.View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    public interface ClickListener {
        void onClick(android.view.View view, int position);

        void onLongClick(android.view.View view, int position);
    }

    static class RecyclerTouchListener implements android.support.v7.widget.RecyclerView.OnItemTouchListener {

        private android.view.GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(android.content.Context context, final android.support.v7.widget.RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new android.view.GestureDetector(context, new android.view.GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(android.view.MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(android.view.MotionEvent e) {
                    android.view.View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(android.support.v7.widget.RecyclerView rv, android.view.MotionEvent e) {

            android.view.View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(android.support.v7.widget.RecyclerView rv, android.view.MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }


    }

    public interface FragmentDrawerListener {
        void onDrawerItemSelected(android.view.View view, int position);
    }
}