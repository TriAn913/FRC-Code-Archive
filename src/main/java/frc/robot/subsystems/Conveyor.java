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
import frc.robot.commands.Conveyor.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;

/**
 * Add your docs here.
 */
public class Conveyor extends SubsystemBase {
  private VictorSPX conveyorbk;

  public Conveyor() {
    conveyorbk = new VictorSPX(RobotMap.Motors.CONVEYOR_BACK);

    //conveyorbk.setSafetyEnabled(true);
    //setDefaultCommand(new ConveyorStop(this));
    setDefaultCommand(new ConveyorControl(this));
  }

  public void setConveyorPwr(double val) {
    conveyorbk.set(ControlMode.PercentOutput,-1*val);
  }
}