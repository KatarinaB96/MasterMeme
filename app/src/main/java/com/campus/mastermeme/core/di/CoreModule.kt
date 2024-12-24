package com.campus.mastermeme.core.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.campus.mastermeme.R
import com.campus.mastermeme.core.data.MemesRepositoryImpl
import com.campus.mastermeme.core.data.local.AvailableMemeEntity
import com.campus.mastermeme.core.data.local.MasterMemeDatabase
import com.campus.mastermeme.core.domain.MemesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreModule = module {

    singleOf(::MemesRepositoryImpl).bind<MemesRepository>()

    single {
        Room.databaseBuilder(
            androidApplication(),
            MasterMemeDatabase::class.java,
            "master_meme_db.db"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    CoroutineScope(Dispatchers.IO).launch {
                        val dao = get<MasterMemeDatabase>().dao
                        dao.insertAvailableMemes(getDefaultMemes())
                    }
                }
            })
            .build()
    }

    single {
        get<MasterMemeDatabase>().dao
    }
}

private fun getDefaultMemes(): List<AvailableMemeEntity> {
    return listOf(
        AvailableMemeEntity(
            id = 1,
            name = "ajtl_46",
            resId = R.drawable.ajtl_46
        ),
        AvailableMemeEntity(
            id = 2,
            name = "bbctk_29",
            resId = R.drawable.bbctk_29
        ),
        AvailableMemeEntity(
            id = 3,
            name = "boardroom_meeting_suggestion_36",
            resId = R.drawable.boardroom_meeting_suggestion_36
        ),
        AvailableMemeEntity(
            id = 4,
            name = "byuiy_33",
            resId = R.drawable.byuiy_33
        ),
        AvailableMemeEntity(
            id = 5,
            name = "c1hh_48",
            resId = R.drawable.c1hh_48
        ),
        AvailableMemeEntity(
            id = 6,
            name = "c9dbh_30",
            resId = R.drawable.c9dbh_30
        ),
        AvailableMemeEntity(
            id = 7,
            name = "change_my_mind_5",
            resId = R.drawable.change_my_mind_5
        ),
        AvailableMemeEntity(
            id = 8,
            name = "clown_applying_makeup_31",
            resId = R.drawable.clown_applying_makeup_31
        ),
        AvailableMemeEntity(
            id = 9,
            name = "disaster_girl_1",
            resId = R.drawable.disaster_girl_1
        ),
        AvailableMemeEntity(
            id = 10,
            name = "eb198_32",
            resId = R.drawable.eb198_32
        ),
        AvailableMemeEntity(
            id = 11,
            name = "epic_handshake_2",
            resId = R.drawable.epic_handshake_2
        ),
        AvailableMemeEntity(
            id = 12,
            name = "eqjd8_12",
            resId = R.drawable.eqjd8_12
        ),
        AvailableMemeEntity(
            id = 13,
            name = "eyvu_45",
            resId = R.drawable.eyvu_45
        ),
        AvailableMemeEntity(
            id = 14,
            name = "f0mvv_16",
            resId = R.drawable.f0mvv_16
        ),
        AvailableMemeEntity(
            id = 15,
            name = "grus_plan_9",
            resId = R.drawable.grus_plan_9
        ),
        AvailableMemeEntity(
            id = 16,
            name = "hide_the_pain_harold_7",
            resId = R.drawable.hide_the_pain_harold_7
        ),
        AvailableMemeEntity(
            id = 17,
            name = "iacv_13",
            resId = R.drawable.iacv_13
        ),
        AvailableMemeEntity(
            id = 18,
            name = "igo27_47",
            resId = R.drawable.igo27_47
        ),
        AvailableMemeEntity(
            id = 19,
            name = "is_this_a_pigeon_21",
            resId = R.drawable.is_this_a_pigeon_21
        ),
        AvailableMemeEntity(
            id = 20,
            name = "i_bet_hes_thinking_about_other_women_10",
            resId = R.drawable.i_bet_hes_thinking_about_other_women_10
        ),
        AvailableMemeEntity(
            id = 21,
            name = "i_was_told_there_would_be_35",
            resId = R.drawable.i_was_told_there_would_be_35
        ),
        AvailableMemeEntity(
            id = 22,
            name = "jack_sparrow_being_chased_23",
            resId = R.drawable.jack_sparrow_being_chased_23
        ),
        AvailableMemeEntity(
            id = 23,
            name = "jgrgn_44",
            resId = R.drawable.jgrgn_44
        ),
        AvailableMemeEntity(
            id = 24,
            name = "left_exit_12_off_ramp_3",
            resId = R.drawable.left_exit_12_off_ramp_3
        ),
        AvailableMemeEntity(
            id = 25,
            name = "leonardo_dicaprio_cheers_24",
            resId = R.drawable.leonardo_dicaprio_cheers_24
        ),
        AvailableMemeEntity(
            id = 26,
            name = "op9wy_25",
            resId = R.drawable.op9wy_25
        ),
        AvailableMemeEntity(
            id = 27,
            name = "otri4_40",
            resId = R.drawable.otri4_40
        ),
        AvailableMemeEntity(
            id = 28,
            name = "p2is_38",
            resId = R.drawable.p2is_38
        ),
        AvailableMemeEntity(
            id = 29,
            name = "qx7sw_49",
            resId = R.drawable.qx7sw_49
        ),
        AvailableMemeEntity(
            id = 30,
            name = "rcrc1_39",
            resId = R.drawable.rcrc1_39
        ),
        AvailableMemeEntity(
            id = 31,
            name = "reqtg_50",
            resId = R.drawable.reqtg_50
        ),
        AvailableMemeEntity(
            id = 32,
            name = "running_away_balloon_14",
            resId = R.drawable.running_away_balloon_14
        ),
        AvailableMemeEntity(
            id = 33,
            name = "sad_pablo_escobar_4",
            resId = R.drawable.sad_pablo_escobar_4
        ),
        AvailableMemeEntity(
            id = 34,
            name = "scared_cat_34",
            resId = R.drawable.scared_cat_34
        ),
        AvailableMemeEntity(
            id = 35,
            name = "soh_20",
            resId = R.drawable.soh_20
        ),
        AvailableMemeEntity(
            id = 36,
            name = "su9f_41",
            resId = R.drawable.su9f_41
        ),
        AvailableMemeEntity(
            id = 37,
            name = "sz4u_18",
            resId = R.drawable.sz4u_18
        ),
        AvailableMemeEntity(
            id = 38,
            name = "t8r9a_26",
            resId = R.drawable.t8r9a_26
        ),
        AvailableMemeEntity(
            id = 39,
            name = "the_rock_driving_8",
            resId = R.drawable.the_rock_driving_8
        ),
        AvailableMemeEntity(
            id = 40,
            name = "third_world_skeptical_kid_11",
            resId = R.drawable.third_world_skeptical_kid_11
        ),
        AvailableMemeEntity(
            id = 41,
            name = "two_buttons_28",
            resId = R.drawable.two_buttons_28
        ),
        AvailableMemeEntity(
            id = 42,
            name = "two_buttons_6",
            resId = R.drawable.two_buttons_6
        ),
        AvailableMemeEntity(
            id = 43,
            name = "u6ylb_19",
            resId = R.drawable.u6ylb_19
        ),
        AvailableMemeEntity(
            id = 44,
            name = "vt4i_27",
            resId = R.drawable.vt4i_27
        ),
        AvailableMemeEntity(
            id = 45,
            name = "w2e5e_17",
            resId = R.drawable.w2e5e_17
        ),
        AvailableMemeEntity(
            id = 46,
            name = "waiting_skeleton_43",
            resId = R.drawable.waiting_skeleton_43
        ),
        AvailableMemeEntity(
            id = 47,
            name = "wxtd1_42",
            resId = R.drawable.wxtd1_42
        ),
        AvailableMemeEntity(
            id = 48,
            name = "yz6z4_22",
            resId = R.drawable.yz6z4_22
        ),
        AvailableMemeEntity(
            id = 49,
            name = "zoa8_15",
            resId = R.drawable.zoa8_15
        )
    )
}

