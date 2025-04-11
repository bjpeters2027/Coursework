#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

typedef enum{
    GREEN = 0,
    RED = 1,
    YELLOW = 2
}LightColor;


typedef struct{
    LightColor current;
    LightColor next;
    int duration;
}State;


void changeLight(State* state);

char* getTime(int pos, int len, char string[]);


int main(){
    FILE *file = fopen("config.txt", "r"); //Initialize the file
    int totalRunTime; //This stores the time for how lon the program should run
    int timeRan = 0; //This stores how long the 

    //This is where the times for each state will be stored
    int mainGTime, mainYTime, sideGTime, sideYTime;

    char line[30];

    //This is reading config.txt
    while(fgets(line, sizeof(line), file)){
        switch (line[0])
        {
        case 'M': //The line is assigning a main street value
            switch (line[8])
            {
            case 'G'://Assigning the green time
                mainGTime = atoi(getTime(18,3,line));
                break;
            
            case 'Y'://Assigning the yellow time
                mainYTime = atoi(getTime(19,3,line));
                break;
            default:
                fprintf(stderr,"Improper input file format\n");
                return 0;
                break;
            }
            break;
        
        case 'S'://The line is assigning a side street value
            switch (line[8])
            {
            case 'G'://Assigning the green time
                sideGTime = atoi(getTime(18,3,line));
                break;
            
            case 'Y'://Assigning the yellow time
                sideYTime = atoi(getTime(19,3,line));
                break;
            default:
                fprintf(stderr,"Improper input file format\n");
                return 0;
                break;
            }
            break;
        
        default:
            fprintf(stderr,"Improper input file format\n");
            return 0;
            break;
        }
    }

    printf("How long should I run?\n");
    scanf("%d", &totalRunTime); //Assigning totalRunTime


    State* mainStreet = malloc(sizeof(State)); //This will manage the state of the main street
    mainStreet->current = GREEN;
    mainStreet->next = YELLOW;
    mainStreet->duration = mainGTime;

    State* sideStreet = malloc(sizeof(State)); //This will manage the state of the side street
    sideStreet->current = RED;
    sideStreet->next = GREEN;
    sideStreet->duration = sideGTime;
    int stateTime = 0;

    //This is the simulation
    while(timeRan <= totalRunTime){
        switch (mainStreet->current)
        {
        case GREEN://Main street light is green
            printf("%d: Side is red\n", timeRan);
            printf("%d: Main is green and will be green for %d seconds\n", timeRan, (mainGTime - stateTime));
	    if(stateTime == mainGTime){
	        stateTime = 0;
            changeLight(mainStreet);
	    }else{
	    stateTime++;
	    }
            break;
        
        case YELLOW://Main street light is yellow
            printf("%d: Main is yellow and will be yellow for %d seconds\n",timeRan,  (mainYTime - stateTime));
            if(stateTime == mainYTime){
                stateTime = 0;
                changeLight(mainStreet);
                changeLight(sideStreet);
            }else{
                stateTime++;
            }
            break;
        
        case RED://Main street light is red therefore side street light is green
            if(stateTime <= sideGTime){
                printf("%d: Main is red\n", timeRan);
                printf("%d: Side is green and will be green for %d seconds\n", timeRan, (sideGTime - stateTime));
                changeLight(sideStreet);
                stateTime++;
            }else{
                printf("%d: Side is yellow and will be yellow for %d seconds\n",timeRan, ((stateTime - sideGTime) + sideYTime));
                if(((stateTime - sideGTime) - sideYTime) == 0){
                    stateTime = 0;
                    changeLight(sideStreet);
                    changeLight(mainStreet);
                }else{
                    stateTime++;
                }
            }
        default:
            break;
        }
    //Increments timeRan and sleeps
	sleep(1);
	timeRan++;
    }
    fclose(file);
}

//This function changes the state of the street passed to it
void changeLight(State* state){
    switch (state->next)
    {
        case GREEN:
        state->current = GREEN;
        state->next = YELLOW;
        break;
    
    case YELLOW:
        state->current = YELLOW;
        state->next = RED;
        break;

    case RED:
        state->current = RED;
        state->next = GREEN;
        break;
    }
}

//This function is used in the reading of config.txt to specifically target the numbers in line
char* getTime(int pos, int len, char string[])
{
    char* substring = malloc(len + 1);
    int i = 0;
    while (i < len) {
        substring[i] = string[pos + i - 1];
        i++;
    }
    substring[i] = '\0';
    return substring;
}
