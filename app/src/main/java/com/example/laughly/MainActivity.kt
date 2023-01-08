package com.example.laughly

import android.graphics.Color.rgb
import android.os.Bundle
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import api.TodoViewModel

import com.example.laughly.ui.theme.LaughLyTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
    TodoView(todoViewModel = TodoViewModel())
        }
    }
}

@Composable
fun TodoView(todoViewModel: TodoViewModel) {

    LaunchedEffect(Unit, block = {
        todoViewModel.getJokeList()
    })

    Scaffold(

        topBar = {
            TopAppBar(
                backgroundColor = Color.Black,
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Jokes",
                            color = Color.White,
                            fontSize = 30.sp,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                },
            )
        },
        content = {
            if (todoViewModel.errorMessage.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp)
                        .background(Color(rgb(192, 192, 192))),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {



                    LazyColumn(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)) {


                        items(todoViewModel.jokeList) { todo ->
                            Column {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(1.dp, 0.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    var text by remember {
                                        mutableStateOf(todo.setup)
                                    }
                                    Box(




                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(9.dp, 0.dp, 9.dp, 0.dp)



                                    ) {
                                        var text=todo.setup;
                                        Column(
                                            Modifier.background(Color(rgb(192,192,192)))
                                        ) {
                                            button(text1 = todo.setup, text2 =todo.punchline)
                                            Spacer(modifier = Modifier.height(7.dp))
                                        }

                                    }
                                    Spacer(modifier = Modifier.width(16.dp))

                                }
                                Divider()
                            }
                        }

                    }
                    Button(onClick = { todoViewModel.getJokeList() },
                    colors = ButtonDefaults.buttonColors(Color.Black)) {
                        Text(text = "Refresh",
                        color=Color.White)
                    }
                }




            } else {
                Text(todoViewModel.errorMessage)
            }

        }
    )
}
@Composable
fun button(
    text1:String,
    text2:String
){
    var choice by remember {
        mutableStateOf(0)
    }
    var thistext by remember {
        mutableStateOf(text1)
    }
    Button(
        onClick = { if(choice==0)choice=1 else choice=0 },
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(Color.White),
        modifier = Modifier.height(90.dp)

    ) {
        Text(
            text = if(choice==0)text1 else text2,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 17.sp
        )
    }
}