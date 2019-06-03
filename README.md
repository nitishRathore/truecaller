# mvvm_demo


The follow MVVM architechture
Model-View-ViewModel architecture consists of 3 parts.
The View gets user’s actions and sends to the ViewModel, or listens live data stream from the ViewModel and provides to the users.
The ViewModel gets user’s actions from the View or provides data to View.
The Model abstracts the data source. View and ViewModel uses that on data stream.

The App Architure also follows Repository pattern:
Repository pattern is layer for data abstraction. The viewmodel will call the getData method but it wont know from where exactly the data is coming. It can come from network call or it can come from local database.
<img src="https://github.com/nitishRathore/mvvm_demo/blob/master/repository.png?sanitize=true&raw=true" />



App uses Rxjava for buisness logic and Livedata for Ui logic as Livedata is lifecycle aware.


App uses BaseActivity and BaseFragment
We have to use DaggerApplication, DaggerAppCompatActivity and DaggerFragment classes for injecting objects with ContributesAndroidInjector annotation.
We have used abstract layoutRes() function in order to get resource layout id from Activity/Fragment which extends BaseActivity/BaseFragment.

Creating Custom ViewModel Factory
ViewModelFactory is a factory that extends ViewModelProvider.Factory in order to provide ViewModel instances to consumer activity/fragment classes. We have injected that class with the ViewModelModule.
