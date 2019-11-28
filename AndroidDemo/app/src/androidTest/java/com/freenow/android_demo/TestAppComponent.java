package com.freenow.android_demo;

import com.freenow.android_demo.dependencies.module.NetworkModule;
import com.freenow.android_demo.dependencies.module.PermissionModule;
import com.freenow.android_demo.dependencies.module.SharedPrefStorageModule;
import com.freenow.android_demo.tests.BaseTest;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {NetworkModule.class, PermissionModule.class, SharedPrefStorageModule.class})
public interface TestAppComponent
{

    void inject(BaseTest test);

}
