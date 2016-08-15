package com.maxblumental.cleanmvp.presenter.fragment;

import com.maxblumental.cleanmvp.presenter.LifecycleCallbackTrigger;

public enum FragmentLifecycleEvents implements LifecycleCallbackTrigger<FragmentLifecycle> {
    ATTACH {
        @Override
        public void call(FragmentLifecycle lifecycle) {
            lifecycle.onAttach();
        }
    },
    CREATE {
        @Override
        public void call(FragmentLifecycle lifecycle) {
            lifecycle.onCreate();
        }
    },
    CREATE_VIEW {
        @Override
        public void call(FragmentLifecycle lifecycle) {
            lifecycle.onCreateView();
        }
    },
    ACTIVITY_CREATED {
        @Override
        public void call(FragmentLifecycle lifecycle) {
            lifecycle.onActivityCreated();
        }
    },
    START {
        @Override
        public void call(FragmentLifecycle lifecycle) {
            lifecycle.onStart();
        }
    },
    RESUME {
        @Override
        public void call(FragmentLifecycle lifecycle) {
            lifecycle.onResume();
        }
    },
    PAUSE {
        @Override
        public void call(FragmentLifecycle lifecycle) {
            lifecycle.onPause();
        }
    },
    STOP {
        @Override
        public void call(FragmentLifecycle lifecycle) {
            lifecycle.onStop();
        }
    },
    DESTROY_VIEW {
        @Override
        public void call(FragmentLifecycle lifecycle) {
            lifecycle.onDestroyView();
        }
    },
    DESTROY {
        @Override
        public void call(FragmentLifecycle lifecycle) {
            lifecycle.onDestroy();
        }
    },
    DETACH {
        @Override
        public void call(FragmentLifecycle lifecycle) {
            lifecycle.onDetach();
        }
    }
}