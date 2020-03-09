package dao

import config.ConferenceManagementConfig

class Lunch extends Event(ConferenceManagementConfig.LUNCH_START_TIME, "Lunch", ConferenceManagementConfig.LUNCH_DURATION_MINUTES)
