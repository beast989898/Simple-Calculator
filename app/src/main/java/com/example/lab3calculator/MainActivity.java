package com.example.lab3calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.data.EvaluationValue;
import com.ezylang.evalex.parser.ParseException;

public class MainActivity extends AppCompatActivity {

    TextView resultText;
    private boolean hasDot = false;
    private boolean enabledDot = false;
    private CharSequence lastValid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.resultText);

        int statusBarColor = Color.parseColor("#FFC0CB");
        getWindow().setStatusBarColor(statusBarColor);



        resultText.setShowSoftInputOnFocus(false);

    }

    // Sample implementation of the onClickNumericalButton (...).
    // Feel free to re-implement or modify.
    @SuppressLint("SetTextI18n")
    public void onClickNumericalButton(View view) {

        // Getting ID of pressed Button
        int pressID = view.getId();

        // Figuring out which button was pressed and updating the represented text field
        // object
        if (pressID == R.id.button0) {
            resultText.setText(resultText.getText() + "0");
        } else if (pressID == R.id.button1) {
            resultText.setText(resultText.getText() + "1");
        } else if (pressID == R.id.button2) {
            resultText.setText(resultText.getText() + "2");
        } else if (pressID == R.id.button3) {
            resultText.setText(resultText.getText() + "3");
        } else if (pressID == R.id.button4) {
            resultText.setText(resultText.getText() + "4");
        } else if (pressID == R.id.button5) {
            resultText.setText(resultText.getText() + "5");
        } else if (pressID == R.id.button6) {
            resultText.setText(resultText.getText() + "6");
        } else if (pressID == R.id.button7) {
            resultText.setText(resultText.getText() + "7");
        } else if (pressID == R.id.button8) {
            resultText.setText(resultText.getText() + "8");
        } else if (pressID == R.id.button9) {
            resultText.setText(resultText.getText() + "9");
        } else if (pressID == R.id.buttonClear) {
            resultText.setText("");
            hasDot = false;
        } else if (pressID == R.id.buttonBack) {
            if (resultText.getText().length() > 0) {
                if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == 'R') {
                    resultText.setText(lastValid);
                } else if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == '.') {
                    resultText.setText(resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                    hasDot = false;
                } else if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == '-'
                        || resultText.getText().toString().charAt(resultText.getText().length() - 1) == '+'
                        || resultText.getText().toString().charAt(resultText.getText().length() - 1) == '*'
                        || resultText.getText().toString().charAt(resultText.getText().length() - 1) == '/') {
                    if (enabledDot) {
                        enabledDot = false;
                        hasDot = true;
                    }
                    resultText.setText(resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                } else {
                    resultText.setText(resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                }
            }
        } else if (pressID == R.id.buttonDot) {
            if (!hasDot) {
                resultText.setText(resultText.getText() + ".");
                hasDot = true;
            }
        } else {
            resultText.setText("ERROR");
        }

    }

    @SuppressLint("SetTextI18n")
    public void onClickFunctionButton(View view) {
        int pressID = view.getId();

        if (pressID == R.id.buttonDiv) {
            if (resultText.getText().length() > 1) {
                if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == '-'
                        || resultText.getText().toString().charAt(resultText.getText().length() - 1) == '+') {
                    if (resultText.getText().toString().charAt(resultText.getText().length() - 2) != '*'
                            && resultText.getText().toString().charAt(resultText.getText().length() - 2) != '/') {
                        resultText.setText(
                                resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                        resultText.setText(resultText.getText() + "/");
                        if (hasDot) {
                            enabledDot = true;
                            hasDot = false;
                        }
                    }
                } else {
                    if (resultText.getText().toString().charAt(resultText.getText().length() - 1) != '.') {
                        if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == '*') {
                            resultText.setText(
                                    resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                        } else if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == '/') {
                            resultText.setText(
                                    resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                        }
                        resultText.setText(resultText.getText() + "/");
                        if (hasDot) {
                            enabledDot = true;
                            hasDot = false;
                        }
                    }
                }
            } else if (resultText.getText().length() == 1) {
                if (resultText.getText().toString().charAt(resultText.getText().length() - 1) != '.') {
                    if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == '*') {
                        resultText.setText(
                                resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                    } else if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == '+') {
                        resultText.setText(
                                resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                    } else if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == '/') {
                        resultText.setText(
                                resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                    }
                    if (resultText.getText().length() != 0 && resultText.getText().charAt(0) != '-') {
                        resultText.setText(resultText.getText() + "/");
                    }
                    if (hasDot) {
                        enabledDot = true;
                        hasDot = false;
                    }
                }
            }

        } else if (pressID == R.id.buttonMul) {
            if (resultText.getText().length() > 1) {
                if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == '-'
                        || resultText.getText().toString().charAt(resultText.getText().length() - 1) == '+') {
                    if (resultText.getText().toString().charAt(resultText.getText().length() - 2) != '*'
                            && resultText.getText().toString().charAt(resultText.getText().length() - 2) != '/') {
                        resultText.setText(
                                resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                        resultText.setText(resultText.getText() + "*");
                        if (hasDot) {
                            enabledDot = true;
                            hasDot = false;
                        }
                    }
                } else {
                    if (resultText.getText().toString().charAt(resultText.getText().length() - 1) != '.') {
                        if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == '*') {
                            resultText.setText(
                                    resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                        } else if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == '/') {
                            resultText.setText(
                                    resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                        }
                        resultText.setText(resultText.getText() + "*");
                        if (hasDot) {
                            enabledDot = true;
                            hasDot = false;
                        }
                    }
                }
            } else if (resultText.getText().length() == 1) {
                if (resultText.getText().toString().charAt(resultText.getText().length() - 1) != '.') {
                    if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == '*') {
                        resultText.setText(
                                resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                    } else if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == '+') {
                        resultText.setText(
                                resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                    } else if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == '/') {
                        resultText.setText(
                                resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                    }
                    if (resultText.getText().length() != 0 && resultText.getText().charAt(0) != '-') {
                        resultText.setText(resultText.getText() + "*");
                    }
                    if (hasDot) {
                        enabledDot = true;
                        hasDot = false;
                    }
                }
            }
        } else if (pressID == R.id.buttonMinus) {
            if (resultText.getText().length() > 0) {
                if (resultText.getText().toString().charAt(resultText.getText().length() - 1) != '.') {
                    if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == '-') {
                        resultText.setText(
                                resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                    } else if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == '+') {
                        resultText.setText(
                                resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                    }
                    resultText.setText(resultText.getText() + "-");
                    if (hasDot) {
                        enabledDot = true;
                        hasDot = false;
                    }
                }
            } else {
                resultText.setText(resultText.getText() + "-");
            }
        } else if (pressID == R.id.buttonPlus) {
            if (resultText.getText().length() > 1) {
                if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == '-'
                        || resultText.getText().toString().charAt(resultText.getText().length() - 1) == '+') {
                    if (resultText.getText().toString().charAt(resultText.getText().length() - 2) != '*'
                            && resultText.getText().toString().charAt(resultText.getText().length() - 2) != '/') {
                        resultText.setText(
                                resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                        resultText.setText(resultText.getText() + "+");
                        if (hasDot) {
                            enabledDot = true;
                            hasDot = false;
                        }
                    }
                } else {
                    if (resultText.getText().toString().charAt(resultText.getText().length() - 1) != '.') {
                        if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == '*') {
                            resultText.setText(
                                    resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                        } else if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == '/') {
                            resultText.setText(
                                    resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                        }
                        resultText.setText(resultText.getText() + "+");
                        if (hasDot) {
                            enabledDot = true;
                            hasDot = false;
                        }
                    }
                }
            } else if (resultText.getText().length() == 1) {
                if (resultText.getText().toString().charAt(resultText.getText().length() - 1) != '.') {
                    if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == '*') {
                        resultText.setText(
                                resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                    } else if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == '-') {
                        resultText.setText(
                                resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                    } else if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == '+') {
                        resultText.setText(
                                resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                    } else if (resultText.getText().toString().charAt(resultText.getText().length() - 1) == '/') {
                        resultText.setText(
                                resultText.getText().toString().substring(0, resultText.getText().length() - 1));
                    }
                    if (resultText.getText().length() != 0) {
                        resultText.setText(resultText.getText() + "+");
                    }
                    if (hasDot) {
                        enabledDot = true;
                        hasDot = false;
                    }
                }
            }
        } else if (pressID == R.id.buttonEq) {
            if (resultText.getText().length() != 0) {
                try {
                    Expression expression = new Expression(resultText.getText().toString());
                    EvaluationValue result = expression.evaluate();
                    double scientificToStandard = Double.parseDouble(String.valueOf(result.getNumberValue()));
                    if (scientificToStandard == (int) scientificToStandard) {
                        int scientificToStandardInt = (int) scientificToStandard;
                        resultText.setText(String.valueOf(scientificToStandardInt));
                    } else {
                        resultText.setText(String.valueOf(Double.parseDouble(String.valueOf(result.getNumberValue()))));
                    }
                    hasDot = resultText.getText().toString().contains(".");
                } catch (EvaluationException | ParseException | NumberFormatException e) {
                    lastValid = resultText.getText();
                    resultText.setText("ERROR");
                    if (lastValid.length() > 0) {
                        if (lastValid.toString().charAt(lastValid.length() - 1) != '.') {
                            hasDot = false;
                        }
                    }
                }
            }

        }
    }

}
