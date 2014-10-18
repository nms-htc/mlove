/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function submitHandle(xhr, status, args, dialogName) {
    if (args.validationFailed) {
        PF(dialogName).jq.effect("shake", {times: 5}, 100);
    } else {
        PF(dialogName).hide();
    }
}