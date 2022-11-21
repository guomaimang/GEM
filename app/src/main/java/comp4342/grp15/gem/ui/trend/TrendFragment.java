package comp4342.grp15.gem.ui.trend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import comp4342.grp15.gem.model.ClientPostMeta;
import comp4342.grp15.gem.databinding.FragmentTrendBinding;

public class TrendFragment extends Fragment {

    private FragmentTrendBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TrendViewModel trendViewModel =
                new ViewModelProvider(this).get(TrendViewModel.class);

        binding = FragmentTrendBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ListView listView= binding.trendListview;

        //Activity成为此LiveData的观察者，LiveData能够监听到Activity的生命周期
        trendViewModel.getPostMetas().observe(getActivity(), new Observer<ArrayList<ClientPostMeta>>() {
            @Override
            public void onChanged(ArrayList<ClientPostMeta> postMetas) {
                listView.setAdapter(new TrendListAdapter(getActivity(), postMetas));
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}