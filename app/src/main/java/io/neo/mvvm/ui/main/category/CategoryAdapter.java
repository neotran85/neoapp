package io.neo.mvvm.ui.main.category;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davemorrissey.labs.subscaleview.ImageSource;

import java.util.ArrayList;

import io.neo.mvvm.R;
import io.neo.mvvm.databinding.ViewItemCategoryBinding;
import io.neo.mvvm.databinding.ViewItemCategoryEmptyBinding;
import io.neo.mvvm.ui.base.BaseViewHolder;

public class CategoryAdapter extends RecyclerView.Adapter <BaseViewHolder> {
    private static final int VIEW_TYPE_NORMAL = 0;
    private static final int VIEW_TYPE_EMPTY = 1;
    private static final int VIEW_TYPE_LOADING = 2;

    private ArrayList<CategoryItemViewModel> categories;

    private View.OnClickListener onClickListener;

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if(categories == null || categories.size() == 0) {
            return 0;
        }
        return categories.size();
    }

    public CategoryAdapter(ArrayList<CategoryItemViewModel> arrayList) {
        this.categories = arrayList;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return getContentHolder(parent);
            case VIEW_TYPE_EMPTY:
                return getEmptyHolder(parent);
            case VIEW_TYPE_LOADING:
                return getLoadingHolder(parent);
            default:
                return getDefaultHolder(parent);
        }
    }

    private CategoryItemLoadingViewHolder getLoadingHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View loadingView = inflater.inflate(R.layout.view_preloading, parent, false);
        return new CategoryItemLoadingViewHolder(loadingView);
    }

    @Override
    public int getItemViewType(int position) {
        if (categories == null) {
            return VIEW_TYPE_LOADING; // loading item
        }
        if (categories != null && categories.size() == 0) {
            return VIEW_TYPE_EMPTY; // empty item
        }
        return VIEW_TYPE_NORMAL;
    }
    private CategoryItemEmptyViewHolder getEmptyHolder(ViewGroup parent) {
        ViewItemCategoryEmptyBinding itemViewBinding = ViewItemCategoryEmptyBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CategoryItemEmptyViewHolder(itemViewBinding);
    }
    private BaseViewHolder getDefaultHolder(ViewGroup parent) {
        return new BaseViewHolder(parent) {
            @Override
            public void onBind(int position) {
                // Do nothing
            }
        };
    }
    private CategoryItemViewHolder getContentHolder(ViewGroup parent) {
        ViewItemCategoryBinding itemViewBinding = ViewItemCategoryBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CategoryItemViewHolder(itemViewBinding);
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public class CategoryItemViewHolder extends BaseViewHolder {

        private ViewItemCategoryBinding mBinding;

        public CategoryItemViewHolder(ViewItemCategoryBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            if (categories != null && categories.size() > 0) {
                final CategoryItemViewModel itemViewModel = categories.get(position);
                mBinding.setViewModel(itemViewModel);
                mBinding.executePendingBindings();
                mBinding.ivThumbnail.setImage(ImageSource.asset(itemViewModel.obImage.get()));
                mBinding.getRoot().setTag(itemViewModel.getCategory());
                mBinding.getRoot().setOnClickListener(onClickListener);
            }
        }

    }
    public class CategoryItemEmptyViewHolder extends BaseViewHolder {
        public CategoryItemEmptyViewHolder(ViewItemCategoryEmptyBinding binding) {
            super(binding.getRoot());
        }

        @Override
        public void onBind(int position) {

        }
    }

    public class CategoryItemLoadingViewHolder extends BaseViewHolder {
        public CategoryItemLoadingViewHolder(View view) {
            super(view);
        }

        @Override
        public void onBind(int position) {

        }
    }
}
