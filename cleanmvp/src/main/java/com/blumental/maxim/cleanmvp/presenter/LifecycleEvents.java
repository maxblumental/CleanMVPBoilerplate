package com.blumental.maxim.cleanmvp.presenter;

public enum LifecycleEvents implements LifecycleCallbackTrigger {
    ATTACH {
        @Override
        public void call(BaseFragmentPresenter<?, ?> presenter) {
            presenter.onAttach();
        }
    },
    CREATE {
        @Override
        public void call(BaseFragmentPresenter<?, ?> presenter) {
            presenter.onCreate();
        }
    },
    CREATE_VIEW {
        @Override
        public void call(BaseFragmentPresenter<?, ?> presenter) {
            presenter.onCreateView();
        }
    },
    ACTIVITY_CREATED {
        @Override
        public void call(BaseFragmentPresenter<?, ?> presenter) {
            presenter.onActivityCreated();
        }
    },
    START {
        @Override
        public void call(BaseFragmentPresenter<?, ?> presenter) {
            presenter.onStart();
        }
    },
    RESUME {
        @Override
        public void call(BaseFragmentPresenter<?, ?> presenter) {
            presenter.onResume();
        }
    },
    PAUSE {
        @Override
        public void call(BaseFragmentPresenter<?, ?> presenter) {
            presenter.onPause();
        }
    },
    STOP {
        @Override
        public void call(BaseFragmentPresenter<?, ?> presenter) {
            presenter.onStop();
        }
    },
    DESTROY_VIEW {
        @Override
        public void call(BaseFragmentPresenter<?, ?> presenter) {
            presenter.onDestroyView();
        }
    },
    DESTROY {
        @Override
        public void call(BaseFragmentPresenter<?, ?> presenter) {
            presenter.onDestroy();
        }
    },
    DETACH {
        @Override
        public void call(BaseFragmentPresenter<?, ?> presenter) {
            presenter.onDetach();
        }
    }
}