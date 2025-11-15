package com.example.justimagine;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardPackageManager {
    private static CardPackageManager instance = new CardPackageManager();
    private Context mContext;
    private List<CardPackage> mPackages;
    private DBHandler db;

    private CardPackageManager(){
        mPackages = new ArrayList<>();
    }

    public static CardPackageManager get(){
        return instance;
    }


    public void setContext(Context context){
        mContext = context;
        db = new DBHandler(mContext);
    }

    public String getName(int i){
        return mPackages.get(i).mName;
    }
    public Integer getNameId(int i){ return mPackages.get(i).mNameId; }
    public Integer getCoverId(int i){ return mPackages.get(i).mCoverId; }
    public Integer getColorId(int i){ return mPackages.get(i).mColorId; }
    public List<Integer> getImages(int i){ return mPackages.get(i).mImages; }

    public boolean isActive(int i){
        return mPackages.get(i).isActive;
    }

    public int getSize(){
        return mPackages.size();
    }

    public void setActive(int position, boolean active) { mPackages.get(position).isActive = active; }

    public void loadDB() {
        mPackages = new ArrayList<>();
//        mPackages.add(new CardPackage("basic_pack", R.string.pack_test_0, true, R.drawable.ic_launcher_background, R.color.pack_test_0, Arrays.asList(new Integer[]{
//                R.drawable.a_abacus_regular,
//                R.drawable.a_acorn_light,
//                R.drawable.a_address_card_light,
//                R.drawable.a_air_freshener_light,
//                R.drawable.a_alien_light,
//                R.drawable.a_american_sign_language_interpreting_light,
//                R.drawable.a_analytics_duotone,
//                R.drawable.a_angry_light,
//                R.drawable.a_apple_alt_light,
//                R.drawable.a_backward_light,
//                R.drawable.a_bacterium_light,
//                R.drawable.a_badge_check_regular,
//                R.drawable.a_basketball_ball_light,
//                R.drawable.a_bat_light,
//                R.drawable.a_bed_alt_light,
//                R.drawable.a_bell_school_light,
//                R.drawable.a_bible_light,
//                R.drawable.a_binoculars_light,
//                R.drawable.a_bluetooth_b_brands,
//                R.drawable.a_bone_light,
//                R.drawable.a_boot_regular,
//                R.drawable.a_brain_light,
//                R.drawable.a_camera_home_light,
//                R.drawable.a_camera_retro_light,
//                R.drawable.a_cannabis_light,
//                R.drawable.a_capsules_light,
//                R.drawable.a_cat_light,
//                R.drawable.a_cheese_swiss_light,
//                R.drawable.a_chess_queen_alt_light,
//                R.drawable.a_clock_light,
//                R.drawable.a_cloud_light,
//                R.drawable.a_cloud_showers_heavy_light,
//                R.drawable.a_comet_light,
//                R.drawable.a_comment_regular,
//                R.drawable.a_croissant_light,
//                R.drawable.a_cube_light,
//                R.drawable.a_cut_regular,
//                R.drawable.a_dice_light,
//                R.drawable.a_dog_light,
//                R.drawable.a_duck_light,
//                R.drawable.a_ear_light,
//                R.drawable.a_egg_light,
//                R.drawable.a_exclamation_solid,
//                R.drawable.a_fan_regular,
//                R.drawable.a_feather_alt_light,
//                R.drawable.a_fire_regular,
//                R.drawable.a_fish_light,
//                R.drawable.a_flask_potion_light,
//                R.drawable.a_flower_regular,
//                R.drawable.a_french_fries_light,
//                R.drawable.a_gavel_light,
//                R.drawable.a_gem_light,
//                R.drawable.a_gift_light,
//                R.drawable.a_gingerbread_man_light,
//                R.drawable.a_globe_europe_light,
//                R.drawable.a_gramophone_light,
//                R.drawable.a_guitar_light,
//                R.drawable.a_hand_holding_water_regular,
//                R.drawable.a_hand_middle_finger_light,
//                R.drawable.a_hand_rock_light,
//                R.drawable.a_hard_hat_light,
//                R.drawable.a_hat_cowboy_regular,
//                R.drawable.a_hat_winter_light,
//                R.drawable.a_hat_witch_light,
//                R.drawable.a_headphones_light,
//                R.drawable.a_helmet_battle_light,
//                R.drawable.a_hockey_mask_light,
//                R.drawable.a_hockey_puck_light,
//                R.drawable.a_home_light,
//                R.drawable.a_ice_cream_light,
//                R.drawable.a_industry_alt_light,
//                R.drawable.a_integral_regular,
//                R.drawable.a_joint_light,
//                R.drawable.a_key_light,
//                R.drawable.a_kite_solid,
//                R.drawable.a_lamp_desk_light,
//                R.drawable.a_landmark_light,
//                R.drawable.a_lemon_light,
//                R.drawable.a_lightbulb_light,
//                R.drawable.a_luchador_light,
//                R.drawable.a_mace_regular,
//                R.drawable.a_megaphone_light,
//                R.drawable.a_menorah_light,
//                R.drawable.a_money_bill_wave_alt_light,
//                R.drawable.a_monkey_light,
//                R.drawable.a_moon_light,
//                R.drawable.a_mosque_light,
//                R.drawable.a_motorcycle_light,
//                R.drawable.a_mountain_light,
//                R.drawable.a_music_light,
//                R.drawable.a_narwhal_light,
//                R.drawable.a_octopus_deploy_brands,
//                R.drawable.a_palette_light,
//                R.drawable.a_pastafarianism_light,
//                R.drawable.a_paw_claws_light,
//                R.drawable.a_pegasus_light,
//                R.drawable.a_pen_light,
//                R.drawable.a_pi_regular,
//                R.drawable.a_pie_light,
//                R.drawable.a_plane_light,
//                R.drawable.a_planet_ringed_solid,
//                R.drawable.a_question_solid,
//                R.drawable.a_rocket_light,
//                R.drawable.a_sack_light,
//                R.drawable.a_satellite_dish_light,
//                R.drawable.a_sausage_regular,
//                R.drawable.a_saxophone_light,
//                R.drawable.a_scarf_light,
//                R.drawable.a_shopping_basket_light,
//                R.drawable.a_shower_regular,
//                R.drawable.a_sickle_light,
//                R.drawable.a_skull_crossbones_regular,
//                R.drawable.a_snake_light,
//                R.drawable.a_snowboarding_light,
//                R.drawable.a_snowflake_light,
//                R.drawable.a_soup_light,
//                R.drawable.a_spa_light,
//                R.drawable.a_sparkles_light,
//                R.drawable.a_spider_light,
//                R.drawable.a_spider_web_light,
//                R.drawable.a_square_root_alt_light,
//                R.drawable.a_star_christmas_solid,
//                R.drawable.a_stethoscope_solid,
//                R.drawable.a_studiovinari_brands,
//                R.drawable.a_sword_light,
//                R.drawable.a_tachometer_fast_light,
//                R.drawable.a_tag_light,
//                R.drawable.a_terminal_regular,
//                R.drawable.a_ticket_regular,
//                R.drawable.a_toilet_light,
//                R.drawable.a_toilet_paper_alt_regular,
//                R.drawable.a_tooth_solid,
//                R.drawable.a_tree_large_light,
//                R.drawable.a_tree_palm_light,
//                R.drawable.a_trees_light,
//                R.drawable.a_trophy_light,
//                R.drawable.a_truck_plow_light,
//                R.drawable.a_tshirt_light,
//                R.drawable.a_usb_drive_light,
//                R.drawable.a_user_astronaut_light,
//                R.drawable.a_user_crown_light,
//                R.drawable.a_vacuum_light,
//                R.drawable.a_water_regular,
//                R.drawable.a_wave_sine_regular,
//                R.drawable.a_wheelchair_regular,
//                R.drawable.a_wind_light,
//                R.drawable.a_wine_bottle_light,
//                R.drawable.a_wine_glass_alt_light })));

        mPackages.add(new CardPackage("basic_pack", R.string.pack_test_0, true, R.drawable.ic_launcher_background, R.color.pack_test_0, Arrays.asList(new Integer[]{
                R.drawable.p_card_basic_cactus,
                R.drawable.p_card_basic_ear,
                R.drawable.p_card_basic_forest,
                R.drawable.p_card_basic_light_bulb,
                R.drawable.p_card_basic_papers,
                R.drawable.p_card_basic_star,
                R.drawable.p_card_basic_ufo,
                R.drawable.p_card_basic_candy,
                R.drawable.p_card_basic_earth,
                R.drawable.p_card_basic_gold,
                R.drawable.p_card_basic_lips,
                R.drawable.p_card_basic_plant,
                R.drawable.p_card_basic_stick,
                R.drawable.p_card_basic_unicorn,
                R.drawable.p_card_basic_chilli,
                R.drawable.p_card_basic_exclamation,
                R.drawable.p_card_basic_hearth,
                R.drawable.p_card_basic_moon,
                R.drawable.p_card_basic_puppet,
                R.drawable.p_card_basic_suit,
                R.drawable.p_card_basic_vampire,
                R.drawable.p_card_basic_bag,
                R.drawable.p_card_basic_crown,
                R.drawable.p_card_basic_eye,
                R.drawable.p_card_basic_house,
                R.drawable.p_card_basic_nose,
                R.drawable.p_card_basic_question,
                R.drawable.p_card_basic_sun,
                R.drawable.p_card_basic_water,
                R.drawable.p_card_basic_bird,
                R.drawable.p_card_basic_dice,
                R.drawable.p_card_basic_fire,
                R.drawable.p_card_basic_ice_cream,
                R.drawable.p_card_basic_note,
                R.drawable.p_card_basic_rose,
                R.drawable.p_card_basic_sword,
                R.drawable.p_card_basic_wizzard,
                R.drawable.p_card_basic_bottle,
                R.drawable.p_card_basic_dress,
                R.drawable.p_card_basic_fish,
                R.drawable.p_card_basic_knife,
                R.drawable.p_card_basic_paint_brush,
                R.drawable.p_card_basic_skull,
                R.drawable.p_card_basic_thunder_cloud,

                R.drawable.p_card_basic_apple,
                R.drawable.p_card_basic_balls,
                R.drawable.p_card_basic_beach,
                R.drawable.p_card_basic_bed,
                R.drawable.p_card_basic_bell,
                R.drawable.p_card_basic_book,
                R.drawable.p_card_basic_cardboard,
                R.drawable.p_card_basic_cat,
                R.drawable.p_card_basic_cheese,
                R.drawable.p_card_basic_croisant,
                R.drawable.p_card_basic_dog,
                R.drawable.p_card_basic_duck,
                R.drawable.p_card_basic_eggplant,
                R.drawable.p_card_basic_explosion,
                R.drawable.p_card_basic_gears_and_bolts,
                R.drawable.p_card_basic_grape,
                R.drawable.p_card_basic_hammer,
                R.drawable.p_card_basic_handcuffs,
                R.drawable.p_card_basic_herbs,
                R.drawable.p_card_basic_highheels,
                R.drawable.p_card_basic_horse,
                R.drawable.p_card_basic_lake,
                R.drawable.p_card_basic_lion,
                R.drawable.p_card_basic_mask,
                R.drawable.p_card_basic_math,
                R.drawable.p_card_basic_meat,
                R.drawable.p_card_basic_mooshroom,
                R.drawable.p_card_basic_moutain,
                R.drawable.p_card_basic_peach,
                R.drawable.p_card_basic_perfume,
                R.drawable.p_card_basic_pinecone,
                R.drawable.p_card_basic_portal,
                R.drawable.p_card_basic_potato,
                R.drawable.p_card_basic_pot,
                R.drawable.p_card_basic_rabbit,
                R.drawable.p_card_basic_ring,
                R.drawable.p_card_basic_shield,
                R.drawable.p_card_basic_snowflake,
                R.drawable.p_card_basic_spider,
                R.drawable.p_card_basic_strawberry,
                R.drawable.p_card_basic_tomato,
                R.drawable.p_card_basic_tower,
                R.drawable.p_card_basic_turtoise,
                R.drawable.p_card_basic_waterfall,
                R.drawable.p_card_basic_wheat,
                R.drawable.p_card_basic_wing,

        })));


        for(CardPackage cardPackage : mPackages){
            Cursor cur = db.getPackages(cardPackage.mName);
            cur.moveToNext();
            if(cur.getCount() > 0){
                cardPackage.isActive  = cur.getInt(2) > 0;
            }
            else{
                db.addPackage(cardPackage);
            }
            cur.close();
        }
    }

    public void saveDB(){
        for(CardPackage cardPackage : mPackages){
            Cursor cur = db.getPackages(cardPackage.mName);
            if(cur.getCount() > 0){
                db.updatePackage(cardPackage);
            }
            else{
                db.addPackage(cardPackage);
            }
            cur.close();
        }
    }
}
