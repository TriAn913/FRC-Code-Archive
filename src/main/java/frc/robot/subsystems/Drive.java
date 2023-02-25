/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.Drive.DriveControl;



/**
 * Add your docs here.
 */
public class Drive extends SubsystemBase {

  private WPI_TalonFX _leftFront;
  private WPI_TalonFX _leftFollower;
  private WPI_TalonFX _rghtFront;  
  private WPI_TalonFX _rghtFollower;  
  private DifferentialDrive drive;
  public Boolean INVERT = false;
  public double aimPreviousError = 0.00f;

  public Drive() {
    _leftFront = new WPI_TalonFX(RobotMap.Motors.LEFT_FRONT);
    _leftFollower = new WPI_TalonFX(RobotMap.Motors.LEFT_FOLLOW);
    _rghtFront = new WPI_TalonFX(RobotMap.Motors.RIGHT_FRONT);
    _rghtFollower = new WPI_TalonFX(RobotMap.Motors.RIGHT_FOLLOW);
    drive = new DifferentialDrive(_leftFront, _rghtFront);

    _rghtFront.configFactoryDefault();
    _rghtFollower.configFactoryDefault();
    _leftFront.configFactoryDefault();
    _leftFollower.configFactoryDefault();

    /* set up followers */
    _rghtFollower.follow(_rghtFront);
    _leftFollower.follow(_leftFront);

    /* [3] flip values so robot moves forward when stick-forward/LEDs-green */
    _rghtFront.setInverted(false); // !< Update this
    _leftFront.setInverted(false); // !< Update this

    /*
      * set the invert of the followers to match their respective master controllers
      */
    _rghtFollower.setInverted(InvertType.FollowMaster);
    _leftFollower.setInverted(InvertType.FollowMaster);

    /*
      * [4] adjust sensor phase so sensor moves positive when Talon LEDs are green
      */
    _rghtFront.setSensorPhase(true);
    _leftFront.setSensorPhase(true);

    /*
      * WPI drivetrain classes defaultly assume left and right are opposite. call
      * this so we can apply + to both sides when moving forward. DO NOT CHANGE
      */
    //drive.setRightSideInverted(false);
    setDefaultCommand(new DriveControl(this));

    _rghtFollower.configNeutralDeadband(0.00);
    _leftFollower.configNeutralDeadband(0.00);
  }

  public void setRaw(double leftVal, double rightVal) {
    if(!RobotMap.Config.ENABLE_MOTORS) return;
    
    drive.tankDrive(leftVal, rightVal);
  }

  public void setArcade(double xSpeed, double zRotation) {
    if(!RobotMap.Config.ENABLE_MOTORS) return;

    drive.arcadeDrive(xSpeed, zRotation);
  }

  public void setNeutral(String type){
    if (type == "Brake") {
      _rghtFront.setNeutralMode(NeutralMode.Brake);
      _rghtFollower.setNeutralMode(NeutralMode.Brake);
      _leftFront.setNeutralMode(NeutralMode.Brake);
      _leftFollower.setNeutralMode(NeutralMode.Brake);
    }
    else if (type == "Coast") {
      _rghtFront.setNeutralMode(NeutralMode.Coast);
      _rghtFollower.setNeutralMode(NeutralMode.Coast);
      _leftFront.setNeutralMode(NeutralMode.Coast);
      _leftFollower.setNeutralMode(NeutralMode.Coast);
    }
  }
  public void setRamp(double time){
    if (time <= 10 && time > 0) {
      _rghtFront.configOpenloopRamp(time);
      _rghtFollower.configOpenloopRamp(time);
      _leftFront.configOpenloopRamp(time);
      _leftFollower.configOpenloopRamp(time);
    }
    else {
      _rghtFront.configOpenloopRamp(0);
      _rghtFollower.configOpenloopRamp(0);
      _leftFront.configOpenloopRamp(0);
      _leftFollower.configOpenloopRamp(0);
    }
  }
  public void setInvert(boolean Invert) {
    _leftFront.setInverted(Invert);
    _leftFollower.setInverted(Invert);
    _rghtFront.setInverted(Invert);
    _rghtFollower.setInverted(Invert);
  }
}