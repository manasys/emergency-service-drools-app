[condition][]The average pulse is {p} in the last {s} seconds=$nextEvents : Number(doubleValue == {p} ) from accumulate( WiiMoteEvent( type == "acc", processed == false, $y: y) over window:time({s}s) from entry-point "patientHeartbeats", average($y))
[condition][]The pulse is greater than {p}/{s} seconds=ArrayList(size > {p} ) from collect( WiiMoteEvent( type == "acc", processed == false, $y: y) over window:time({s}s) from entry-point "patientHeartbeats")
[consequence][]Send 'No Signal Signs' Alert=notifier.notifyMapEventsListeners(MapEventsNotifier.EVENT_TYPE.NO_VITAL_SIGNS, "Warning, patient without vital signs ");
[consequence][]Send 'Heart Attack' Alert=notifier.notifyMapEventsListeners(MapEventsNotifier.EVENT_TYPE.HEART_ATTACK, "Warning, patient suffering a heart attack ");
