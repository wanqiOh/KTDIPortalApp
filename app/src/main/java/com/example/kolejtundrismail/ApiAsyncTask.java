package com.example.kolejtundrismail;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * An asynchronous task that handles the Google Calendar API call.
 * Placing the API calls in their own task ensures the UI stays responsive.
 */

/**
 * Created by miguel on 5/29/15.
 */

public class ApiAsyncTask extends AsyncTask<Void, Void, Void> {
    private Main4Activity mActivity;
    private String name;
    private String place;
    private String date;
    private String desc;

    /**
     * Constructor.
     *
     * @param activity MainActivity that spawned this task.
     */
    ApiAsyncTask(Main4Activity activity, String name, String place, String date, String desc) {
        this.mActivity = activity;
        this.name = name;
        this.place = place;
        this.date = date;
        this.desc = desc;
    }

    /**
     * Background task to call Google Calendar API.
     *
     * @param params no parameters needed for this task.
     */
    @Override
    protected Void doInBackground(Void... params) {
        try {
            mActivity.clearResultsText();
            mActivity.updateResultsText(CreateEvent());

        } catch (final GooglePlayServicesAvailabilityIOException availabilityException) {
            mActivity.showGooglePlayServicesAvailabilityErrorDialog(
                    availabilityException.getConnectionStatusCode());

        } catch (UserRecoverableAuthIOException userRecoverableException) {
            mActivity.startActivityForResult(
                    userRecoverableException.getIntent(),
                    Main4Activity.REQUEST_AUTHORIZATION);

        } catch (IOException e) {
            mActivity.updateStatus("The following error occurred: " +
                    e.getMessage());
        }
        return null;
    }

    /**
     * Fetch a list of the next 10 events from the primary calendar.
     *
     * @return List of Strings describing returned events.
     * @throws IOException
     */
    private List<String> getDataFromApi() throws IOException {
        // List the next 10 events from the primary calendar.
        DateTime now = new DateTime(System.currentTimeMillis());
        List<String> eventStrings = new ArrayList<String>();
        Events events = mActivity.mService.events().list("primary")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();

        for (Event event : items) {
            DateTime start = event.getStart().getDateTime();
            if (start == null) {
                // All-day events don't have start times, so just use
                // the start date.
                start = event.getStart().getDate();
            }
            eventStrings.add(
                    String.format("%s (%s)", event.getSummary(), start));
        }
        return eventStrings;
    }

    private List<String> CreateEvent() throws IOException{
        //    Create Event
        // Refer to the Java quickstart on how to setup the environment:
// https://developers.google.com/calendar/quickstart/java
// Change the scope to CalendarScopes.CALENDAR and delete any stored
// credentials.
        List<String> eventStrings = new ArrayList<String>();
        Event event = new Event()
                .setSummary(this.name)
                .setLocation(this.place)
                .setDescription("Merit: "+this.desc);
        DateFormat format = new SimpleDateFormat("dd MMMM yyyy",Locale.ENGLISH);
        Date startDate = null;
        try {
            startDate = format.parse(this.date);
            Log.d("CREATION", Long.toString(startDate.getTime()));
            Long startTime = startDate.getTime();
            Long endTime = startTime + 3600;
            DateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'10:00:00");
            String isoStartDate = isoFormat.format(startTime);
            DateTime startDateTime = new DateTime(isoStartDate);
            String isoEndDate = isoFormat.format(endTime);
            DateTime endDateTime = new DateTime(isoEndDate);

            EventDateTime start = new EventDateTime()
                    .setDateTime(startDateTime)
                    .setTimeZone("Asia/Kuala_Lumpur");
            event.setStart(start);

            EventDateTime end = new EventDateTime()
                    .setDateTime(endDateTime)
                    .setTimeZone("Asia/Kuala_Lumpur");
            event.setEnd(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        String[] recurrence = new String[]{"RRULE:FREQ=DAILY;COUNT=2"};
//        event.setRecurrence(Arrays.asList(recurrence));

//        EventAttendee[] attendees = new EventAttendee[]{
//                new EventAttendee().setEmail("lpage@example.com"),
//                new EventAttendee().setEmail("sbrin@example.com"),
//        };
//        event.setAttendees(Arrays.asList(attendees));

//        EventReminder[] reminderOverrides = new EventReminder[]{
//                new EventReminder().setMethod("email").setMinutes(24 * 60),
//                new EventReminder().setMethod("popup").setMinutes(10),
//        };
//        Event.Reminders reminders = new Event.Reminders()
//                .setUseDefault(false)
//                .setOverrides(Arrays.asList(reminderOverrides));
//        event.setReminders(reminders);

        String calendarId = "primary";
        event = mActivity.mService.events().insert(calendarId, event).execute();
        eventStrings.add(
                String.format("%s", event.getHtmlLink())
        );
        return eventStrings;
//        Log.d("CREATION","Event created: "+ event.getHtmlLink());
        //System.out.printf("Event created: %s\n", event.getHtmlLink());

    }
}