package com.example.tictoktoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Fields

    // 10 in This Game Mean Draw And No Action
    int [] StateArray = new int[9];
    String StatePlay = "شروع بازی";
    boolean flag = false;
    int StateO = 0 , StateX = 0 ;
    int FlagStateWin = 10 ;
    boolean flagVisibilityResetBtn = false;
    // Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_stateplayer);
        setContentView(R.layout.activity_main);
        SetFirstValueOfField();
        SetAllValueInStateArrayTen();
        SetStateTxtPlayer();
    }
    // Methods
    public void SetFirstValueOfField()
    {
        StatePlay = "شروع بازی";
        flag = false;
        StateO = 0 ;
        StateX = 0 ;
        FlagStateWin = 10 ;
        flagVisibilityResetBtn = false;
    };
    public void SetAllValueInStateArrayTen()
    {
        for (int i =0 ; i<9 ;i++) {
            StateArray[i]=10 ;
        }
    }
    // Set New Content Of Button And TextView Of Activity StatePlayer
    public void SetStateTxtPlayer()
    {
        Button Start = findViewById(R.id.btn_start);
        TextView textViewPlayerState = findViewById(R.id.TxtPlayerOResult);
        textViewPlayerState.setText(String.valueOf(StateO));
        textViewPlayerState = findViewById(R.id.TxtPlayerXResult);
        textViewPlayerState.setText(String.valueOf(StateX));
        Start.setText(String.valueOf(StatePlay));
    }
    // Call By Click On Image Place In Activity Main
    public void ClickOnImage(View view)
    {
        ImageView image = (ImageView)view;
        image.setTranslationY(-150);
        image.animate().setDuration(300).translationY(-1500);
        ChangeImage(view);
        image.animate().translationYBy(150).rotation(3600).setDuration(250);
        String Tag = image.getTag().toString();
        int tagState = Integer.parseInt(Tag);
        if(CheckWinner())
        {
            if(StateArray[tagState]==1)
            {
                FlagStateWin = 1;
                StateO++ ;
                ChangeEnviromentToActivityStatePlayer();
            }
            else
            {
                StateX++;
                FlagStateWin = 0;
                ChangeEnviromentToActivityStatePlayer();
            }
        }
        else if(CheckDraw())
        {
            ChangeEnviromentToActivityStatePlayer();
        }
    }
    // By Click Of Player , Picture Changed
    public void ChangeImage(View view)
    {
        ImageView imageView = (ImageView)view;
        String Tag = imageView.getTag().toString();
        int tagState = Integer.parseInt(Tag);
        if(flag )
        {
            if(StateArray[tagState]!=0)
            {
                imageView.setImageResource(R.drawable.x);
                StateArray[tagState]= 0 ;
            }
            else if(StateArray[tagState]!=1)
            {
                imageView.setImageResource(R.drawable.o);
                StateArray[tagState]= 1 ;
            }
            flag = false;
        }
        else
        {
            if(StateArray[tagState]!=1)
            {
                imageView.setImageResource(R.drawable.o);
                StateArray[tagState]= 1 ;
            }
            else if(StateArray[tagState]!=0)
            {
                imageView.setImageResource(R.drawable.o);
                StateArray[tagState]= 0 ;
            }
            flag = true;
        }
    }
    // Check Game Have a Winner Or Not
    public boolean CheckWinner()
    {
        int [][] WiningPostion = {{0,1,2},{0,3,6},{0,4,8},{2,4,6},{6,7,8},{1,4,7},{2,5,8},{3,4,5}};
        for (int[] Checker : WiningPostion)
        {
            int first = Checker[0] , Secound = Checker[1] , Third = Checker[2];
            if(StateArray[first]== StateArray[Secound] && StateArray[Secound] == StateArray[Third] && StateArray[Third]!=10)
            {
                return true;
            }
        }
        return  false;
    }
    // Check Result Of Game Was Draw Or Not
    public boolean CheckDraw()
    {
        for (int i : StateArray) {
            if(i==10)
            {
                return false;
            }
        }
        return true;
    }
    // Change Activity To Main Activity
    public void SetActivityMain(View view)
    {
        setContentView(R.layout.activity_main);
    }
    // Change Activity To Activity StatePlayer
    public void SetActivityStatePlayer()
    {
        setContentView(R.layout.activity_stateplayer);
    }
    // Set Null Resource OF All Image in Activity Main
    public void SetNullImage()
    {
        ConstraintLayout constraintLayout = findViewById(R.id.ConstrainLayoutActivityMain);
        for (int i=0 ; i< constraintLayout.getChildCount();i++)
        {
            try {
                ImageView imageView = (ImageView) constraintLayout.getChildAt(i);
                if(imageView.getTag()!=null)
                {
                    imageView.setImageDrawable(null);
                }
            }
            catch (Exception e)
            {

            }
        }
    }
    public void ChangeEnviromentToActivityStatePlayer()
    {
        SetNullImage();
        StatePlay = "ادامه بازی";
        SetActivityStatePlayer();
            Button buttonReset = findViewById(R.id.BtnReset);
            buttonReset.setVisibility(View.VISIBLE);
        SetStateTxtPlayer();

        if(FlagStateWin==1)
        {
            Toast.makeText(this, "بازیکن O برنده بازی شد", Toast.LENGTH_SHORT).show();
        }
        else if (FlagStateWin==0)
        {
            Toast.makeText(this, "بازیکن X برنده بازی شد", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "بازی با تساوی خاتمه یافت", Toast.LENGTH_SHORT).show();
        }
        // Reset StateWin
        FlagStateWin = 10 ;
        SetAllValueInStateArrayTen();
        // Change Player
        if(flag)
        {
            flag = false;
        }
        else{
            flag = true;
        }
    }
    public void ResetGame(View view)
    {
        SetFirstValueOfField();
        Button buttonReset = findViewById(R.id.BtnReset);
        buttonReset.setVisibility(View.GONE);
        SetStateTxtPlayer();
    }
}