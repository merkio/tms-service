package com.company.tms.commons.dto.report.notification;

import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotificationRuleDTO {

    private Integer id;

    private Integer projectId;

    private List<String> emailRecipients = new ArrayList<>();

    private List<String> slackRecipients = new ArrayList<>();

    private String runName;

    private LocalTime slackNotificationStartTime;

    private LocalTime slackNotificationEndTime;

    private Boolean notifyOwners = false;

}
