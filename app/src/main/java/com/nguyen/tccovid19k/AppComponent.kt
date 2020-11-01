package com.nguyen.tccovid19k

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun inject(fragment: TotalStatsFragment4)
    fun inject(fragment: RegionStatsFragment4)
    fun inject(fragment: CovidMapFragment4)
}