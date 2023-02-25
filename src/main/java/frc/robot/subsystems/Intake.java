/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.commands.Intake.IntakeStop;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;

/**
 * Add your docs here.
 */

public class Intake extends SubsystemBase {

  public enum IntakeState {IN, OUT, STOPPED};
  
  private VictorSPX intakeBot;
  public IntakeState intakeState;

  public Intake() {
    intakeBot = new VictorSPX(RobotMap.Motors.INTAKE);
    //intakeBot.setSafetyEnabled(true);
    setDefaultCommand(new IntakeStop(this));
  }

  // intake controls
  
  public void setIntakePwr(double val) {
    intakeBot.set(ControlMode.PercentOutput,val);
  }

  // NOTE: do not use - slow refresh rate
  // public void setIntakePwr(IntakeState state) {
  //   switch(state) {
   //    case IN: setIntakePwr(RobotMap.Constants.Intake.IN_PWR);
   //    case OUT: setIntakePwr(RobotMap.Constants.Intake.OUT_PWR);
   //    case STOPPED: setIntakePwr(0.0f);
    // }

     // intakeState = state;
   //}
}
