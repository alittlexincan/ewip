package com.zhxu.info.basic.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 历史灾情
 */
@Table(name = "base_disaster_history")
@Getter
@Setter
@Entity
public class DisasterHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 64)
    private String id;

    @Column(name = "status",length = 64)
    private String status;

    @Column(name = "province",length = 64)
    private String province;

    @Column(name = "city",length = 64)
    private String city;

    @Column(name = "report_area",length = 64)
    private String reportArea;

    @Column(name = "report_areaCode",length = 64)
    private String reportAreaCode;

    @Column(name = "happenDisaster_area",length = 64)
    private String happenDisasterArea;

    @Column(name = "event_code",length = 64)
    private String eventCode;

    @Column(name = "disasterType",length = 64)
    private String disasterType;

    @Column(name = "disasterType_old",length = 64)
    private String disasterTypeOld;

    @Column(name = "severity",length = 64)
    private String severity;

    @Column(name = "color",length = 64)
    private String color;

    @Column(name = "level",length = 64)
    private String level;

    @Column(name = "damage",length = 64)
    private String damage;

    @Column(name = "lon",length = 64)
    private String lon;

    @Column(name = "lat",length = 64)
    private String lat;

    @Column(name = "monitor_people",length = 64)
    private String monitorPeople;

    @Column(name = "monitor_time" )
    private Date monitorTime;

    @Column(name = "monitor_organ",length = 64)
    private String monitorOrgan;

    @Column(name = "contact",length = 64)
    private String contact;

    @Column(name = "disasterName",length = 64)
    private String disasterName;

    @Column(name = "accompany_disaster",length = 64)
    private String accompanyDisaster;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "meteorological_elements",length = 64)
    private String meteorologicalElements;

    @Column(name = "influence",length = 64)
    private String influence;

    @Column(name = "disaster_people",length = 20)
    private Integer disasterPeople;

    @Column(name = "death_people",length = 20)
    private Integer deathPeople;

    @Column(name = "missing_people",length = 20)
    private Integer missingPeople;

    @Column(name = "injured_people",length = 20)
    private Integer injuredPeople;

    @Column(name = "trapped_people",length = 20)
    private Integer trappedPeople;

    @Column(name = "waterDifficulty_people",length = 20)
    private Integer waterDifficultyPeople;

    @Column(name = "remove_people",length = 20)
    private Integer removePeople;

    @Column(name = "collapse_house",length = 20)
    private Integer collapseHouse;

    @Column(name = "damage_house",length = 20)
    private Integer damageHouse;

    @Column(name = "school",length = 64)
    private String school;

    @Column(name = "economic_loss",length = 64)
    private String economicLoss;

    @Column(name = "pipeDamage_length",length = 64)
    private String pipeDamageLength;

    @Column(name = "disaster_crops",length = 64)
    private String disasterCrops;

    @Column(name = "disaster_acreage",length = 64)
    private String disasterAcreage;

    @Column(name = "causeDisaster_acreage",length = 64)
    private String causeDisasterAcreage;

    @Column(name = "cropFailure_acreage",length = 64)
    private String cropFailureAcreage;

    @Column(name = "grain_loss",length = 64)
    private String grainLoss;

    @Column(name = "greenhouse_loss",length = 64)
    private String greenhouseLoss;

    @Column(name = "agriculturalEconomy_loss",length = 64)
    private String agriculturalEconomyLoss;

    @Column(name = "reservoir_num",length = 64)
    private String reservoirNum;

    @Column(name = "pasture_influence",length = 64)
    private String pastureInfluence;

    @Column(name = "pastureDisaster_acreage",length = 64)
    private String pastureDisasterAcreage;

    @Column(name = "death_beast",length = 64)
    private String deathBeast;

    @Column(name = "death_poultry",length = 64)
    private String deathPoultry;

    @Column(name = "waterDifficulty_beast",length = 64)
    private String waterDifficultyBeast;

    @Column(name = "pastureEconomy_loss",length = 64)
    private String pastureEconomyLoss;

    @Column(name = "dryReservoir_num",length = 64)
    private String dryReservoirNum;

    @Column(name = "damageBigReservoir",length = 64)
    private String damageBigReservoir;

    @Column(name = "damageMediumReservoir",length = 64)
    private String damageMediumReservoir;

    @Column(name = "damageSmallReservoir",length = 64)
    private String damageSmallReservoir;

    @Column(name = "damageDam",length = 64)
    private String damageDam;

    @Column(name = "damageChannel_len",length = 64)
    private String damageChannelLen;

    @Column(name = "dike_breach",length = 64)
    private String dikeBreach;

    @Column(name = "water_information",length = 64)
    private String waterInformation;

    @Column(name = "waterEconomy_loss",length = 64)
    private String waterEconomyLoss;

    @Column(name = "rivers_information",length = 64)
    private String riversInformation;

    @Column(name = "stop_factory",length = 64)
    private String stopFactory;

    @Column(name = "mountain_information",length = 64)
    private String mountainInformation;

    @Column(name = "waterLogging",length = 64)
    private String waterLogging;

    @Column(name = "disaster_geologic",length = 64)
    private String disasterGeologic;

    @Column(name = "forest_loss",length = 64)
    private String forestLoss;

    @Column(name = "forest_acreage",length = 64)
    private String forestAcreage;

    @Column(name = "forestEconomy_loss",length = 64)
    private String forestEconomyLoss;

    @Column(name = "stopElectric_num",length = 64)
    private String stopElectricNum;

    @Column(name = "shipSinking_num",length = 64)
    private String shipSinkingNum;

    @Column(name = "shipSinking_weight",length = 64)
    private String shipSinkingWeight;

    @Column(name = "fishery_acreage",length = 64)
    private String fisheryAcreage;

    @Column(name = "fisheryEconomy_loss",length = 64)
    private String fisheryEconomyLoss;

    @Column(name = "communicationInterrupt_num",length = 64)
    private String communicationInterruptNum;

    @Column(name = "flightDelays_num",length = 64)
    private String flightDelaysNum;

    @Column(name = "transportationOutage_time",length = 64)
    private String transportationOutageTime;

    @Column(name = "vehicleDamage_num",length = 64)
    private String vehicleDamageNum;

    @Column(name = "railwayDamage_len",length = 64)
    private String railwayDamageLen;

    @Column(name = "highwayDamage_len",length = 64)
    private String highwayDamageLen;

    @Column(name = "transportShipSinking",length = 64)
    private String transportShipSinking;

    @Column(name = "road_blockage",length = 64)
    private String roadBlockage;

    @Column(name = "stranded_passengers",length = 64)
    private String strandedPassengers;

    @Column(name = "trafficEconomy_loss",length = 64)
    private String trafficEconomyLoss;

    @Column(name = "audio",length = 64)
    private String audio;

    @Column(name = "electric_pole",length = 64)
    private String electricPole;

    @Column(name = "power_tower",length = 64)
    private String powerTower;

    @Column(name = "electricBreak_len",length = 64)
    private String electricBreakLen;

    @Column(name = "electricEconomy_time",length = 64)
    private String electricEconomyTime;

    @Column(name = "electricEconomy_loss",length = 64)
    private String electricEconomyLoss;

    @Column(name = "audio_explanation",length = 64)
    private String audioExplanation;

    @Column(name = "communicationStop_time",length = 64)
    private String communicationStopTime;

    @Column(name = "communicationEconomy_loss",length = 64)
    private String communicationEconomyLoss;

    @Column(name = "bridgeDamage_num",length = 64)
    private String bridgeDamageNum;

    @Column(name = "other_influences",length = 64)
    private String otherInfluences;

    @Column(name = "picture",length = 64)
    private String picture;

    @Column(name = "picture_explanation",length = 64)
    private String pictureExplanation;

    @Column(name = "video",length = 64)
    private String video;

    @Column(name = "video_explanation",length = 64)
    private String videoExplanation;

    @Column(name = "meteorological_level",length = 64)
    private String meteorologicalLevel;

    @Column(name = "disaster_level",length = 64)
    private String disasterLevel;

    @Column(name = "cultivated_acreage",length = 64)
    private String cultivatedAcreage;

    @Column(name = "total_population",length = 64)
    private String totalPopulation;

    @Column(name = "gdp",length = 64)
    private String gdp;

    @Column(name = "happen_area",length = 64)
    private String happenArea;

    @Column(name = "flightsCancel",length = 64)
    private String flightsCancel;

    @Column(name = "warnPublic_describe",length = 64)
    private String warnPublicDescribe;

    @Column(name = "data_sources",length = 64)
    private String dataSources;

    @Column(name = "remarks",length = 64)
    private String remarks;

    @Column(name = "filling_person",length = 64)
    private String fillingPerson;

    @Column(name = "handle_person",length = 64)
    private String handlePerson;

    @Column(name = "confirming_person",length = 64)
    private String confirmingPerson;

    @Column(name = "approval",length = 64)
    private String approval;

    @Column(name = "phone",length = 64)
    private String phone;

    @Column(name = "filling_unit",length = 64)
    private String fillingUnit;

    @Column(name = "return_num",length = 64)
    private String returnNum;

    @Column(name = "return_reason",length = 64)
    private String returnReason;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "createUser", referencedColumnName = "id")
    private Employee createUser;

    @ManyToOne
    @JoinColumn(name = "updateUser", referencedColumnName = "id")
    private Employee updateUser;

}