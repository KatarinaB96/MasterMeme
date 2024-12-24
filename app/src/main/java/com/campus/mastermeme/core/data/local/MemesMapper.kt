package com.campus.mastermeme.core.data.local

import com.campus.mastermeme.R
import com.campus.mastermeme.core.domain.models.AvailableMeme
import com.campus.mastermeme.core.domain.models.Meme

fun MemeEntity.toDomainModel(): Meme {
    return Meme(
        id = id,
        name = name,
        imageUri = imageUri,
        isFavorite = isFavorite,
        createdAt = timestamp
    )
}

fun Meme.toEntity(): MemeEntity {
    return MemeEntity(
        id = id,
        name = name,
        imageUri = imageUri,
        isFavorite = isFavorite,
        timestamp = createdAt
    )
}

fun AvailableMemeEntity.toDomainModel(): AvailableMeme {
    val drawableResId = when (id) {
        1 -> R.drawable.ajtl_46
        2 -> R.drawable.bbctk_29
        3 -> R.drawable.boardroom_meeting_suggestion_36
        4 -> R.drawable.byuiy_33
        5 -> R.drawable.c1hh_48
        6 -> R.drawable.c9dbh_30
        7 -> R.drawable.change_my_mind_5
        8 -> R.drawable.clown_applying_makeup_31
        9 -> R.drawable.disaster_girl_1
        10 -> R.drawable.eb198_32
        11 -> R.drawable.epic_handshake_2
        12 -> R.drawable.eqjd8_12
        13 -> R.drawable.eyvu_45
        14 -> R.drawable.f0mvv_16
        15 -> R.drawable.grus_plan_9
        16 -> R.drawable.hide_the_pain_harold_7
        17 -> R.drawable.iacv_13
        18 -> R.drawable.igo27_47
        19 -> R.drawable.is_this_a_pigeon_21
        20 -> R.drawable.i_bet_hes_thinking_about_other_women_10
        21 -> R.drawable.i_was_told_there_would_be_35
        22 -> R.drawable.jack_sparrow_being_chased_23
        23 -> R.drawable.jgrgn_44
        24 -> R.drawable.left_exit_12_off_ramp_3
        25 -> R.drawable.leonardo_dicaprio_cheers_24
        26 -> R.drawable.op9wy_25
        27 -> R.drawable.otri4_40
        28 -> R.drawable.p2is_38
        29 -> R.drawable.qx7sw_49
        30 -> R.drawable.rcrc1_39
        31 -> R.drawable.reqtg_50
        32 -> R.drawable.running_away_balloon_14
        33 -> R.drawable.sad_pablo_escobar_4
        34 -> R.drawable.scared_cat_34
        35 -> R.drawable.soh_20
        36 -> R.drawable.su9f_41
        37 -> R.drawable.sz4u_18
        38 -> R.drawable.t8r9a_26
        39 -> R.drawable.the_rock_driving_8
        40 -> R.drawable.third_world_skeptical_kid_11
        41 -> R.drawable.two_buttons_28
        42 -> R.drawable.two_buttons_6
        43 -> R.drawable.u6ylb_19
        44 -> R.drawable.vt4i_27
        45 -> R.drawable.w2e5e_17
        46 -> R.drawable.waiting_skeleton_43
        47 -> R.drawable.wxtd1_42
        48 -> R.drawable.yz6z4_22
        49 -> R.drawable.zoa8_15
        else -> throw IllegalArgumentException("Unknown id: $id")
    }
    return AvailableMeme(
        id = id,
        name = name,
        resId = drawableResId
    )
}
