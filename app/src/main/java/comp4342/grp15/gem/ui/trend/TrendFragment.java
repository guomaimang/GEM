package comp4342.grp15.gem.ui.trend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import comp4342.grp15.gem.databinding.FragmentTrendBinding;

public class TrendFragment extends Fragment {

    private FragmentTrendBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TrendViewModel homeViewModel =
                new ViewModelProvider(this).get(TrendViewModel.class);

        binding = FragmentTrendBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ListView  listView= binding.trendListview;
        // homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}