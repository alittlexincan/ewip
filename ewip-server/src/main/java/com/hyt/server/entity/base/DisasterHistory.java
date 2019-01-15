package com.hyt.server.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.Date;

@ApiModel(value = "DisasterHistory",description = "历史灾情")
@Table(name = "base_disaster_history")
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
    @JSONField(format="yyyy-MM-dd")
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
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "start_time")
    private Date startTime;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
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

    @Column(name = "createUser",length = 64)
    private String createUser;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "updateUser",length = 64)
    private String updateUser;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time")
    private Date updateTime;

    private String areaName;
    private String createUserName;
    private String updateUserName;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getReportArea() {
        return reportArea;
    }

    public void setReportArea(String reportArea) {
        this.reportArea = reportArea;
    }

    public String getReportAreaCode() {
        return reportAreaCode;
    }

    public void setReportAreaCode(String reportAreaCode) {
        this.reportAreaCode = reportAreaCode;
    }

    public String getHappenDisasterArea() {
        return happenDisasterArea;
    }

    public void setHappenDisasterArea(String happenDisasterArea) {
        this.happenDisasterArea = happenDisasterArea;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getDisasterType() {
        return disasterType;
    }

    public void setDisasterType(String disasterType) {
        this.disasterType = disasterType;
    }

    public String getDisasterTypeOld() {
        return disasterTypeOld;
    }

    public void setDisasterTypeOld(String disasterTypeOld) {
        this.disasterTypeOld = disasterTypeOld;
    }


    public String getDisasterName() {
        return disasterName;
    }

    public void setDisasterName(String disasterName) {
        this.disasterName = disasterName;
    }

    public String getAccompanyDisaster() {
        return accompanyDisaster;
    }

    public void setAccompanyDisaster(String accompanyDisaster) {
        this.accompanyDisaster = accompanyDisaster;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getMeteorologicalElements() {
        return meteorologicalElements;
    }

    public void setMeteorologicalElements(String meteorologicalElements) {
        this.meteorologicalElements = meteorologicalElements;
    }

    public String getInfluence() {
        return influence;
    }

    public void setInfluence(String influence) {
        this.influence = influence;
    }

    public Integer getDisasterPeople() {
        return disasterPeople;
    }

    public void setDisasterPeople(Integer disasterPeople) {
        this.disasterPeople = disasterPeople;
    }

    public Integer getDeathPeople() {
        return deathPeople;
    }

    public void setDeathPeople(Integer deathPeople) {
        this.deathPeople = deathPeople;
    }

    public Integer getMissingPeople() {
        return missingPeople;
    }

    public void setMissingPeople(Integer missingPeople) {
        this.missingPeople = missingPeople;
    }

    public Integer getInjuredPeople() {
        return injuredPeople;
    }

    public void setInjuredPeople(Integer injuredPeople) {
        this.injuredPeople = injuredPeople;
    }

    public Integer getTrappedPeople() {
        return trappedPeople;
    }

    public void setTrappedPeople(Integer trappedPeople) {
        this.trappedPeople = trappedPeople;
    }

    public Integer getWaterDifficultyPeople() {
        return waterDifficultyPeople;
    }

    public void setWaterDifficultyPeople(Integer waterDifficultyPeople) {
        this.waterDifficultyPeople = waterDifficultyPeople;
    }

    public Integer getRemovePeople() {
        return removePeople;
    }

    public void setRemovePeople(Integer removePeople) {
        this.removePeople = removePeople;
    }

    public Integer getCollapseHouse() {
        return collapseHouse;
    }

    public void setCollapseHouse(Integer collapseHouse) {
        this.collapseHouse = collapseHouse;
    }

    public Integer getDamageHouse() {
        return damageHouse;
    }

    public void setDamageHouse(Integer damageHouse) {
        this.damageHouse = damageHouse;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEconomicLoss() {
        return economicLoss;
    }

    public void setEconomicLoss(String economicLoss) {
        this.economicLoss = economicLoss;
    }

    public String getPipeDamageLength() {
        return pipeDamageLength;
    }

    public void setPipeDamageLength(String pipeDamageLength) {
        this.pipeDamageLength = pipeDamageLength;
    }

    public String getDisasterCrops() {
        return disasterCrops;
    }

    public void setDisasterCrops(String disasterCrops) {
        this.disasterCrops = disasterCrops;
    }

    public String getDisasterAcreage() {
        return disasterAcreage;
    }

    public void setDisasterAcreage(String disasterAcreage) {
        this.disasterAcreage = disasterAcreage;
    }

    public String getCauseDisasterAcreage() {
        return causeDisasterAcreage;
    }

    public void setCauseDisasterAcreage(String causeDisasterAcreage) {
        this.causeDisasterAcreage = causeDisasterAcreage;
    }

    public String getCropFailureAcreage() {
        return cropFailureAcreage;
    }

    public void setCropFailureAcreage(String cropFailureAcreage) {
        this.cropFailureAcreage = cropFailureAcreage;
    }

    public String getGrainLoss() {
        return grainLoss;
    }

    public void setGrainLoss(String grainLoss) {
        this.grainLoss = grainLoss;
    }

    public String getGreenhouseLoss() {
        return greenhouseLoss;
    }

    public void setGreenhouseLoss(String greenhouseLoss) {
        this.greenhouseLoss = greenhouseLoss;
    }

    public String getAgriculturalEconomyLoss() {
        return agriculturalEconomyLoss;
    }

    public void setAgriculturalEconomyLoss(String agriculturalEconomyLoss) {
        this.agriculturalEconomyLoss = agriculturalEconomyLoss;
    }

    public String getReservoirNum() {
        return reservoirNum;
    }

    public void setReservoirNum(String reservoirNum) {
        this.reservoirNum = reservoirNum;
    }

    public String getPastureInfluence() {
        return pastureInfluence;
    }

    public void setPastureInfluence(String pastureInfluence) {
        this.pastureInfluence = pastureInfluence;
    }

    public String getPastureDisasterAcreage() {
        return pastureDisasterAcreage;
    }

    public void setPastureDisasterAcreage(String pastureDisasterAcreage) {
        this.pastureDisasterAcreage = pastureDisasterAcreage;
    }

    public String getDeathBeast() {
        return deathBeast;
    }

    public void setDeathBeast(String deathBeast) {
        this.deathBeast = deathBeast;
    }

    public String getDeathPoultry() {
        return deathPoultry;
    }

    public void setDeathPoultry(String deathPoultry) {
        this.deathPoultry = deathPoultry;
    }

    public String getWaterDifficultyBeast() {
        return waterDifficultyBeast;
    }

    public void setWaterDifficultyBeast(String waterDifficultyBeast) {
        this.waterDifficultyBeast = waterDifficultyBeast;
    }

    public String getPastureEconomyLoss() {
        return pastureEconomyLoss;
    }

    public void setPastureEconomyLoss(String pastureEconomyLoss) {
        this.pastureEconomyLoss = pastureEconomyLoss;
    }

    public String getDryReservoirNum() {
        return dryReservoirNum;
    }

    public void setDryReservoirNum(String dryReservoirNum) {
        this.dryReservoirNum = dryReservoirNum;
    }

    public String getDamageBigReservoir() {
        return damageBigReservoir;
    }

    public void setDamageBigReservoir(String damageBigReservoir) {
        this.damageBigReservoir = damageBigReservoir;
    }

    public String getDamageMediumReservoir() {
        return damageMediumReservoir;
    }

    public void setDamageMediumReservoir(String damageMediumReservoir) {
        this.damageMediumReservoir = damageMediumReservoir;
    }

    public String getDamageSmallReservoir() {
        return damageSmallReservoir;
    }

    public void setDamageSmallReservoir(String damageSmallReservoir) {
        this.damageSmallReservoir = damageSmallReservoir;
    }

    public String getDamageDam() {
        return damageDam;
    }

    public void setDamageDam(String damageDam) {
        this.damageDam = damageDam;
    }

    public String getDamageChannelLen() {
        return damageChannelLen;
    }

    public void setDamageChannelLen(String damageChannelLen) {
        this.damageChannelLen = damageChannelLen;
    }

    public String getDikeBreach() {
        return dikeBreach;
    }

    public void setDikeBreach(String dikeBreach) {
        this.dikeBreach = dikeBreach;
    }

    public String getWaterInformation() {
        return waterInformation;
    }

    public void setWaterInformation(String waterInformation) {
        this.waterInformation = waterInformation;
    }

    public String getWaterEconomyLoss() {
        return waterEconomyLoss;
    }

    public void setWaterEconomyLoss(String waterEconomyLoss) {
        this.waterEconomyLoss = waterEconomyLoss;
    }

    public String getRiversInformation() {
        return riversInformation;
    }

    public void setRiversInformation(String riversInformation) {
        this.riversInformation = riversInformation;
    }

    public String getStopFactory() {
        return stopFactory;
    }

    public void setStopFactory(String stopFactory) {
        this.stopFactory = stopFactory;
    }

    public String getMountainInformation() {
        return mountainInformation;
    }

    public void setMountainInformation(String mountainInformation) {
        this.mountainInformation = mountainInformation;
    }

    public String getWaterLogging() {
        return waterLogging;
    }

    public void setWaterLogging(String waterLogging) {
        this.waterLogging = waterLogging;
    }

    public String getDisasterGeologic() {
        return disasterGeologic;
    }

    public void setDisasterGeologic(String disasterGeologic) {
        this.disasterGeologic = disasterGeologic;
    }

    public String getForestLoss() {
        return forestLoss;
    }

    public void setForestLoss(String forestLoss) {
        this.forestLoss = forestLoss;
    }

    public String getForestAcreage() {
        return forestAcreage;
    }

    public void setForestAcreage(String forestAcreage) {
        this.forestAcreage = forestAcreage;
    }

    public String getForestEconomyLoss() {
        return forestEconomyLoss;
    }

    public void setForestEconomyLoss(String forestEconomyLoss) {
        this.forestEconomyLoss = forestEconomyLoss;
    }

    public String getStopElectricNum() {
        return stopElectricNum;
    }

    public void setStopElectricNum(String stopElectricNum) {
        this.stopElectricNum = stopElectricNum;
    }

    public String getShipSinkingNum() {
        return shipSinkingNum;
    }

    public void setShipSinkingNum(String shipSinkingNum) {
        this.shipSinkingNum = shipSinkingNum;
    }

    public String getShipSinkingWeight() {
        return shipSinkingWeight;
    }

    public void setShipSinkingWeight(String shipSinkingWeight) {
        this.shipSinkingWeight = shipSinkingWeight;
    }

    public String getFisheryAcreage() {
        return fisheryAcreage;
    }

    public void setFisheryAcreage(String fisheryAcreage) {
        this.fisheryAcreage = fisheryAcreage;
    }

    public String getFisheryEconomyLoss() {
        return fisheryEconomyLoss;
    }

    public void setFisheryEconomyLoss(String fisheryEconomyLoss) {
        this.fisheryEconomyLoss = fisheryEconomyLoss;
    }

    public String getCommunicationInterruptNum() {
        return communicationInterruptNum;
    }

    public void setCommunicationInterruptNum(String communicationInterruptNum) {
        this.communicationInterruptNum = communicationInterruptNum;
    }

    public String getFlightDelaysNum() {
        return flightDelaysNum;
    }

    public void setFlightDelaysNum(String flightDelaysNum) {
        this.flightDelaysNum = flightDelaysNum;
    }

    public String getTransportationOutageTime() {
        return transportationOutageTime;
    }

    public void setTransportationOutageTime(String transportationOutageTime) {
        this.transportationOutageTime = transportationOutageTime;
    }

    public String getVehicleDamageNum() {
        return vehicleDamageNum;
    }

    public void setVehicleDamageNum(String vehicleDamageNum) {
        this.vehicleDamageNum = vehicleDamageNum;
    }

    public String getRailwayDamageLen() {
        return railwayDamageLen;
    }

    public void setRailwayDamageLen(String railwayDamageLen) {
        this.railwayDamageLen = railwayDamageLen;
    }

    public String getHighwayDamageLen() {
        return highwayDamageLen;
    }

    public void setHighwayDamageLen(String highwayDamageLen) {
        this.highwayDamageLen = highwayDamageLen;
    }

    public String getTransportShipSinking() {
        return transportShipSinking;
    }

    public void setTransportShipSinking(String transportShipSinking) {
        this.transportShipSinking = transportShipSinking;
    }

    public String getRoadBlockage() {
        return roadBlockage;
    }

    public void setRoadBlockage(String roadBlockage) {
        this.roadBlockage = roadBlockage;
    }

    public String getStrandedPassengers() {
        return strandedPassengers;
    }

    public void setStrandedPassengers(String strandedPassengers) {
        this.strandedPassengers = strandedPassengers;
    }

    public String getTrafficEconomyLoss() {
        return trafficEconomyLoss;
    }

    public void setTrafficEconomyLoss(String trafficEconomyLoss) {
        this.trafficEconomyLoss = trafficEconomyLoss;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getElectricPole() {
        return electricPole;
    }

    public void setElectricPole(String electricPole) {
        this.electricPole = electricPole;
    }

    public String getPowerTower() {
        return powerTower;
    }

    public void setPowerTower(String powerTower) {
        this.powerTower = powerTower;
    }

    public String getElectricBreakLen() {
        return electricBreakLen;
    }

    public void setElectricBreakLen(String electricBreakLen) {
        this.electricBreakLen = electricBreakLen;
    }

    public String getElectricEconomyTime() {
        return electricEconomyTime;
    }

    public void setElectricEconomyTime(String electricEconomyTime) {
        this.electricEconomyTime = electricEconomyTime;
    }

    public String getElectricEconomyLoss() {
        return electricEconomyLoss;
    }

    public void setElectricEconomyLoss(String electricEconomyLoss) {
        this.electricEconomyLoss = electricEconomyLoss;
    }

    public String getAudioExplanation() {
        return audioExplanation;
    }

    public void setAudioExplanation(String audioExplanation) {
        this.audioExplanation = audioExplanation;
    }

    public String getCommunicationStopTime() {
        return communicationStopTime;
    }

    public void setCommunicationStopTime(String communicationStopTime) {
        this.communicationStopTime = communicationStopTime;
    }

    public String getCommunicationEconomyLoss() {
        return communicationEconomyLoss;
    }

    public void setCommunicationEconomyLoss(String communicationEconomyLoss) {
        this.communicationEconomyLoss = communicationEconomyLoss;
    }

    public String getBridgeDamageNum() {
        return bridgeDamageNum;
    }

    public void setBridgeDamageNum(String bridgeDamageNum) {
        this.bridgeDamageNum = bridgeDamageNum;
    }

    public String getOtherInfluences() {
        return otherInfluences;
    }

    public void setOtherInfluences(String otherInfluences) {
        this.otherInfluences = otherInfluences;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPictureExplanation() {
        return pictureExplanation;
    }

    public void setPictureExplanation(String pictureExplanation) {
        this.pictureExplanation = pictureExplanation;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVideoExplanation() {
        return videoExplanation;
    }

    public void setVideoExplanation(String videoExplanation) {
        this.videoExplanation = videoExplanation;
    }

    public String getMeteorologicalLevel() {
        return meteorologicalLevel;
    }

    public void setMeteorologicalLevel(String meteorologicalLevel) {
        this.meteorologicalLevel = meteorologicalLevel;
    }

    public String getDisasterLevel() {
        return disasterLevel;
    }

    public void setDisasterLevel(String disasterLevel) {
        this.disasterLevel = disasterLevel;
    }

    public String getCultivatedAcreage() {
        return cultivatedAcreage;
    }

    public void setCultivatedAcreage(String cultivatedAcreage) {
        this.cultivatedAcreage = cultivatedAcreage;
    }

    public String getTotalPopulation() {
        return totalPopulation;
    }

    public void setTotalPopulation(String totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    public String getGdp() {
        return gdp;
    }

    public void setGdp(String gdp) {
        this.gdp = gdp;
    }

    public String getHappenArea() {
        return happenArea;
    }

    public void setHappenArea(String happenArea) {
        this.happenArea = happenArea;
    }

    public String getFlightsCancel() {
        return flightsCancel;
    }

    public void setFlightsCancel(String flightsCancel) {
        this.flightsCancel = flightsCancel;
    }

    public String getWarnPublicDescribe() {
        return warnPublicDescribe;
    }

    public void setWarnPublicDescribe(String warnPublicDescribe) {
        this.warnPublicDescribe = warnPublicDescribe;
    }

    public String getDataSources() {
        return dataSources;
    }

    public void setDataSources(String dataSources) {
        this.dataSources = dataSources;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFillingPerson() {
        return fillingPerson;
    }

    public void setFillingPerson(String fillingPerson) {
        this.fillingPerson = fillingPerson;
    }

    public String getHandlePerson() {
        return handlePerson;
    }

    public void setHandlePerson(String handlePerson) {
        this.handlePerson = handlePerson;
    }

    public String getConfirmingPerson() {
        return confirmingPerson;
    }

    public void setConfirmingPerson(String confirmingPerson) {
        this.confirmingPerson = confirmingPerson;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFillingUnit() {
        return fillingUnit;
    }

    public void setFillingUnit(String fillingUnit) {
        this.fillingUnit = fillingUnit;
    }

    public String getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(String returnNum) {
        this.returnNum = returnNum;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getMonitorPeople() {
        return monitorPeople;
    }

    public void setMonitorPeople(String monitorPeople) {
        this.monitorPeople = monitorPeople;
    }

    public Date getMonitorTime() {
        return monitorTime;
    }

    public void setMonitorTime(Date monitorTime) {
        this.monitorTime = monitorTime;
    }

    public String getMonitorOrgan() {
        return monitorOrgan;
    }

    public void setMonitorOrgan(String monitorOrgan) {
        this.monitorOrgan = monitorOrgan;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}