# Kotlin-SSLCommerz-Payment
At first we have to register in SSLCommerz for SendBox(store-id & store- password)

Then downlode the android sdk Library: from SSLCommerz

Put it to project-> app-> libs

Add Dependency ....build.gradle(app module)

implementation 'androidx.localbroadcastmanager:localbroadcastmanager:1.0.0'    
implementation (name: 'sslCommerzSdk', ext:'aar')     
implementation 'com.google.code.gson:gson:2.8.6'     
implementation 'com.github.chnouman:AwesomeDialog:1.0.5'    

Add Dependency ....build.gradle(project)
 jcenter()
        maven { url 'https://jitpack.io' }
        flatDir {
            dirs 'libs'
        }
        
        
