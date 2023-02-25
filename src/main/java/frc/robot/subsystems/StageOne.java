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
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import frc.robot.commands.Elevator.StageOneIdle;

/**
 * Add your docs here.
 */

public class StageOne extends SubsystemBase {

  private VictorSPX stageOne;
 
  public StageOne() {
    stageOne = new VictorSPX(RobotMap.Motors.STAGEONE);
    setDefaultCommand(new StageOneIdle(this));
    //stageOne.setSafetyEnabled(true);
  }

  // intake controls
  
  public void setPwr(double val) {
    stageOne.set(ControlMode.PercentOutput,val);
  }
}
