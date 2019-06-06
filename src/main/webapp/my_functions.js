

function  degreeToText(degree){
    if (degree>337.5) return 'North';
    if (degree>292.5) return 'North West';
    if(degree>247.5) return 'West';
    if(degree>202.5) return 'South West';
    if(degree>157.5) return 'South';
    if(degree>122.5) return 'South East';
    if(degree>67.5) return 'East';
    if(degree>22.5){return 'North East';}
    return 'North';
}

