import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import BasicRuleEdit from "./BasicRuleEdit";
import AdminNavbar from "../../shared/AdminNavbar";
import { RulesService } from "../../services/RulesService";
import TemplatesMenu from "./templates/TemplatesMenu";
import TemplateDisplay from "./templates/TemplateDisplay";

function Rule() {
  const [rule, setRule] = useState({
    content: undefined,
    path: undefined,
  });
  const [activeTemplate, setActiveTemplate] = useState(0);
  const [templateVariables, setTemplateVariables] = useState([
    {},
    { amenity: "ELEVATOR", value: undefined },
    { place: "KINDERGARTEN", value: undefined },
    { heating: "FURNACE", value: undefined },
    { pet: "CATS", value: undefined },
  ]);
  const [validateMessage, setValidateMessage] = useState(undefined);

  const location = useLocation();

  // ****************************************************************************************************
  // Initial data
  // ****************************************************************************************************

  useEffect(() => {
    async function fetchRule() {
      console.log(location);
      const response = await RulesService.get(location.search);
      console.log(response.data);
      setRule(response.data);
    }
    fetchRule();
  }, []);

  // ****************************************************************************************************
  // Value change handling
  // ****************************************************************************************************

  const handleFormInputChange = (name) => (event) => {
    const val = event.target.value;
    setRule({ ...rule, [name]: val });
  };

  const handleTemplateFormInputChange = (name) => (event) => {
    event.preventDefault();
    const val = event.target.value;
    setTemplateVariables((prevState) => {
      const newItems = [...prevState];
      newItems[activeTemplate][name] = val;
      return newItems;
    });
  };

  const handleTemplateChange = (id) => {
    setActiveTemplate(id);
  };

  useEffect(() => {
    let content = "";
    switch (activeTemplate) {
      case 1:
        content = `package rs.ac.uns.ftn.sbnz.rules

import rs.ac.uns.ftn.sbnz.models.drools.PropertyWithScore;
import rs.ac.uns.ftn.sbnz.models.enums.Amenity;
        
rule "Calculate amenity score - ${templateVariables[1].amenity}"
  when
      $ps: PropertyWithScore(
          scaler.getFiredRules() not contains "Calculate amenity score - ${templateVariables[1].amenity}",
          property.getAmenities() contains Amenity.${templateVariables[1].amenity}
        );
  then
      System.out.println(String.format("Adding ${templateVariables[1].amenity} amenity score for %s", $ps.getProperty().getId()));

      double score = ${templateVariables[1].value} * $ps.getScaler().getElevatorScale();
      $ps.setScore($ps.getScore() + score);

      System.out.println(String.format("Score for %s is %s", $ps.getProperty().getId(), $ps.getScore()));
      $ps.getScaler().getFiredRules().add("Calculate amenity score - ${templateVariables[1].amenity}");
      update($ps);
end`;
        setRule({ ...rule, content: content });
        break;
      case 2:
        content = `package rs.ac.uns.ftn.sbnz.rules

import rs.ac.uns.ftn.sbnz.models.drools.PropertyWithScore;
import rs.ac.uns.ftn.sbnz.models.PlaceOfInterest;
import rs.ac.uns.ftn.sbnz.models.enums.TypeOfPlace;
    
rule "Calculate ${templateVariables[2].place} distance score - LOWER"
    when
        $poi: PlaceOfInterest(typeOfPlace.equals(TypeOfPlace.${templateVariables[2].place}))
        $ps: PropertyWithScore(
            scaler.getFiredRules() not contains String.format("Calculate ${templateVariables[2].place} distance score %s - LOWER", $poi.getId())
        )
        eval($poi.calculateDistance($ps.getProperty()) < $ps.getScaler().getNearbyDistanceFactor())
    then
        System.out.println(String.format("Calculating ${templateVariables[2].place} %s distance score for: %s", $poi.getId(), $ps.getProperty().getId()));
        double score = ${templateVariables[2].value} * $ps.getScaler().getShoppingCenterScale();

        $ps.setScore($ps.getScore() + score);
        System.out.println(String.format("Score for %s is %s", $ps.getProperty().getId(), $ps.getScore()));
        $ps.getScaler().getFiredRules().add(String.format("Calculate ${templateVariables[2].place} distance score %s - LOWER", $poi.getId()));
        update($ps);
end

rule "Calculate ${templateVariables[2].place} distance score - HIGHER"
  when
      $poi: PlaceOfInterest(typeOfPlace.equals(TypeOfPlace.SHOPPING_CENTER))
      $ps: PropertyWithScore(
          scaler.getFiredRules() not contains String.format("Calculate ${templateVariables[2].place} distance score %s - HIGHER", $poi.getId())
      )
      eval($poi.calculateDistance($ps.getProperty()) >= $ps.getScaler().getNearbyDistanceFactor())
  then
      System.out.println(String.format("Calculating ${templateVariables[2].place} %s distance score for: %s", $poi.getId(), $ps.getProperty().getId()));
      double score = $ps.getScaler().getNearbyDistanceFactor() / $poi.calculateDistance($ps.getProperty()) * $ps.getScaler().getShoppingCenterScale();

      $ps.setScore($ps.getScore() + score);
      System.out.println(String.format("Score for %s is %s", $ps.getProperty().getId(), $ps.getScore()));
      $ps.getScaler().getFiredRules().add(String.format("Calculate ${templateVariables[2].place} distance score %s - HIGHER", $poi.getId()));
      update($ps);
end`;
        setRule({ ...rule, content: content });
        break;
      case 3:
        content = `package rs.ac.uns.ftn.sbnz.rules

import rs.ac.uns.ftn.sbnz.models.drools.PropertyWithScore;
import rs.ac.uns.ftn.sbnz.models.enums.Heating;
        
rule "Calculate heating score - ${templateVariables[3].heating}"
    when
        $ps: PropertyWithScore(
            scaler.getFiredRules() not contains "Calculate heating score - ${templateVariables[3].heating}",
            property.getHeating() == Heating.${templateVariables[3].heating}
        );
    then
        System.out.println(String.format("Adding ${templateVariables[3].heating} heating score for %s", $ps.getProperty().getId()));

        double score = ${templateVariables[3].value} * $ps.getScaler().getHeatingScale();
        $ps.setScore($ps.getScore() + score);

        System.out.println(String.format("Score for %s is %s", $ps.getProperty().getId(), $ps.getScore()));
        $ps.getScaler().getFiredRules().add("Calculate heating score - ${templateVariables[3].heating}");
        update($ps);
end`;
        setRule({ ...rule, content: content });
        break;
      case 4:
        content = `package rs.ac.uns.ftn.sbnz.rules

import rs.ac.uns.ftn.sbnz.models.drools.PropertyWithScore;
import rs.ac.uns.ftn.sbnz.models.enums.PetStatus;
  
rule "Calculate pet score - ${templateVariables[4].pet}"
  when
      $ps: PropertyWithScore(
          scaler.getFiredRules() not contains "Calculate pet score - ${templateVariables[4].pet}",
          property.getAllowedPets() contains PetStatus.${templateVariables[4].pet}
        );
  then
      System.out.println(String.format("Adding ${templateVariables[4].pet} pet score for %s", $ps.getProperty().getId()));

      double score = ${templateVariables[4].value} * $ps.getScaler().getPetScale();
      $ps.setScore($ps.getScore() + score);

      System.out.println(String.format("Score for %s is %s", $ps.getProperty().getId(), $ps.getScore()));
      $ps.getScaler().getFiredRules().add("Calculate pet score - ${templateVariables[4].pet}");
      update($ps);
end`;
        setRule({ ...rule, content: content });
        break;
      default:
        break;
    }
  }, [activeTemplate, templateVariables, rule.name]);

  // ****************************************************************************************************
  // API handling
  // ****************************************************************************************************

  const handleSave = async () => {
    setValidateMessage("Validating...");
    if (!validate()) return;
    await RulesService.modifyRule(rule)
      .then(() => setValidateMessage("Validation succesful.\nRule saved."))
      .catch(function (error) {
        if (error.response) {
          let errorMessages = "Validation failed.";
          const data = error.response.data;
          console.log(data);

          data.map((err) => (errorMessages += "\n" + err.text));

          console.log(errorMessages);
          setValidateMessage(errorMessages);
        }
      });
  };

  const handleValidate = async () => {
    setValidateMessage("Validating...");
    if (!validate()) return;
    RulesService.validateRule(rule)
      .then(() => setValidateMessage("Validation succesful."))
      .catch(function (error) {
        if (error.response) {
          let errorMessages = "Validation failed.";
          const data = error.response.data;
          console.log(data);

          data.map((err) => (errorMessages += "\n" + err.text));

          console.log(errorMessages);
          setValidateMessage(errorMessages);
        }
      });
  };

  const validate = () => {
    console.log(rule);
    if (!rule.path || rule.path.trim() === "") {
      setValidateMessage("File name can't be empty");
      return false;
    }
    if (!rule.content || rule.content.trim() === "") {
      setValidateMessage("Rule can't be empty");
      return false;
    }
    return true;
  };

  return (
    <>
      <AdminNavbar />
      {location.search && (
        <BasicRuleEdit
          rule={rule}
          validateMessage={validateMessage}
          inputChange={handleFormInputChange}
          save={handleSave}
          validate={handleValidate}
        />
      )}
      {!location.search && (
        <div className="columns">
          <div className="column is-one-fifth">
            <TemplatesMenu
              activeTemplate={activeTemplate}
              setActiveTemplate={handleTemplateChange}
            />
          </div>
          <div className="column" style={{ marginTop: "3vh" }}>
            <TemplateDisplay
              activeTemplate={activeTemplate}
              inputChange={handleTemplateFormInputChange}
              variables={templateVariables}
            />
            <BasicRuleEdit
              rule={rule}
              validateMessage={validateMessage}
              inputChange={handleFormInputChange}
              save={handleSave}
              validate={handleValidate}
            />
          </div>
        </div>
      )}
    </>
  );
}

export default Rule;
