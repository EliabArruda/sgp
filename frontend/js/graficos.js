function renderGrafico(nPendentes, nIndeferidos, nDeferidos) {
    /*
    console.log("nPendentes", nPendentes);
    console.log("nIndeferidos", nIndeferidos);
    console.log("nDeferidos", nDeferidos);
    */
    var xValues = ["Pendentes", "Indeferidos", "Deferidos"];
    var yValues = [nPendentes, nIndeferidos, nDeferidos];
    var barColors = [
        "#e9c04e",
        "#e94e4e",
        "#55e861"
    ];

    //console.log("Vai desenhar o chart....");
    var ctx = document.getElementById("myChart");
    //console.log(ctx);

    new Chart("myChart", {
        type: "pie",
        data: {
        labels: xValues,
        datasets: [{
            backgroundColor: barColors,
            data: yValues
        }]},
        options: {
            toolTip:{
                fontSize: 30,
            },
            legend: {
                display: false
            },
            title: {
                display: false,
                /*position: 'left',*/
                text: "Atendimentos"
            },
            plugins: {
                tooltip: {
                    titleFont: {
                        size: 100
                    },
                    bodyFont: {
                        size: 50
                    },
                    footerFont: {
                        size: 20 // there is no footer by default
                    }
                }
            },
            tooltips: {
              mode: 'x',
              intersect: false,
              callbacks: {
                footer: function(tooltipItem, data) {
                  return 'some text'
                }
              }
            }
        }
    });
};