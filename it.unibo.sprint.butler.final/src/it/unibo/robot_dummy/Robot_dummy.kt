/* Generated by AN DISI Unibo */ 
package it.unibo.robot_dummy

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Robot_dummy ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						solve("consult('sysRules.pl')","") //set resVar	
					}
					 transition( edgeName="goto",targetState="waitReply", cond=doswitch() )
				}	 
				state("waitReply") { //this:State
					action { //it:State
					}
					 transition(edgeName="t01",targetState="persistReply",cond=whenDispatch("replyPresence"))
				}	 
				state("persistReply") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("replyPresence(STATUS)"), Term.createTerm("replyPresence(STATUS)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								println("$name in ${currentState.stateName} | $currentMsg")
								solve("assert(result(${payloadArg(0)}))","") //set resVar	
						}
					}
					 transition( edgeName="goto",targetState="waitReply", cond=doswitch() )
				}	 
			}
		}
}
