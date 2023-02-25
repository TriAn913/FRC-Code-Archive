/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.Controller;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.RobotMap;

/**
 * Joystick class wrapper for Logitech F310 and Xbox 360/One controllers
 */
public class CustomGamepad extends Joystick {

    private static final double joystickThreshold = 0.05;
    private static final double triggerThreshold = 0.05;

    public JoystickButton a, b, x, y;
    public JoystickButton lb, rb;
    public JoystickButton start, back;
    public JoystickButton lefJoystickButton, righJoystickButton;
    public CustomDPad dpad;

    public CustomGamepad(final int port) {
        super(port);

        a  = new JoystickButton(this, RobotMap.xInputGamepad.BTN_A);
        b  = new JoystickButton(this, RobotMap.xInputGamepad.BTN_B);
        x  = new JoystickButton(this, RobotMap.xInputGamepad.BTN_X);
        y  = new JoystickButton(this, RobotMap.xInputGamepad.BTN_Y);

        lb = new JoystickButton(this, RobotMap.xInputGamepad.BTN_LB);
        rb = new JoystickButton(this, RobotMap.xInputGamepad.BTN_RB);


        start = new JoystickButton(this, RobotMap.xInputGamepad.BTN_START);
        back  = new JoystickButton(this, RobotMap.xInputGamepad.BTN_BACK);
        
        lefJoystickButton = new JoystickButton(this, RobotMap.xInputGamepad.BTN_LEFT_JOYSTICK);
        righJoystickButton = new JoystickButton(this, RobotMap.xInputGamepad.BTN_RIGHT_JOYSTICK);
        dpad = new CustomDPad(this);
    }

    private double applyThreshold(double val) {
        return Math.abs(val) < joystickThreshold ? 0.0 : val;
    }

    public double rightStickX() {
        // this.getRawAxis(0);
        return applyThreshold(this.getRawAxis(RobotMap.xInputGamepad.RIGHT_X_AXIS));
    }

    public double rightStickY() {
        return applyThreshold(this.getRawAxis(RobotMap.xInputGamepad.RIGHT_Y_AXIS));
    }

    public double leftStickX() {
        return applyThreshold(this.getRawAxis(RobotMap.xInputGamepad.LEFT_X_AXIS));
    }

    public double leftStickY() {
        return applyThreshold(this.getRawAxis(RobotMap.xInputGamepad.LEFT_Y_AXIS));
    }

    public boolean leftTriggerPressed() {
        return this.getRawAxis(RobotMap.xInputGamepad.LEFT_TRIGGER) > triggerThreshold;
    }

    public boolean rightTriggerPressed() {
        return this.getRawAxis(RobotMap.xInputGamepad.RIGHT_TRIGGER) > triggerThreshold;
    }

    public double leftTrigger() {
        return leftTriggerPressed() ? this.getRawAxis(RobotMap.xInputGamepad.LEFT_TRIGGER) : 0;
    }

    public double rightTrigger() {
        return rightTriggerPressed() ? this.getRawAxis(RobotMap.xInputGamepad.RIGHT_TRIGGER) : 0;
    }

    public boolean buttonA(){
        return this.a.get();
    }
    public int buttonrb(){
        return this.rb.get() ? 1 : 0;
    }
    public int buttonlb(){
        return this.lb.get() ? 1 : 0;
    }
}