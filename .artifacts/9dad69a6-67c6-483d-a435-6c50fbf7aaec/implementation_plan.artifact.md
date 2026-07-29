# Implementation Plan - Fix IllegalStateException in MainActivity

The application is crashing because a `TextView` is added as a direct child of a `FragmentContainerView` in `activity_main.xml`. `FragmentContainerView` only supports fragments and will throw an `IllegalStateException` if it contains other views.

## User Review Required

> [!IMPORTANT]
> I am proposing to wrap the `TextView` and `FragmentContainerView` in a `ConstraintLayout` to allow them to coexist in the Activity's layout. If the `TextView` was intended to be inside a fragment instead, please let me know.

## Proposed Changes

### UI Layout

#### [MODIFY] [activity_main.xml](file:///D:/StudyAndroid/app/src/main/res/layout/activity_main.xml)
- Change the root element from `FragmentContainerView` to `ConstraintLayout`.
- Move the `FragmentContainerView` inside the `ConstraintLayout`.
- Add constraints to the `TextView` and `FragmentContainerView` to position them correctly.

## Verification Plan

### Manual Verification
1. Deploy the app to a device/emulator.
2. Verify that the app no longer crashes on startup.
3. Verify that "Xin chào Kotlin!" is displayed at the top and the `HomeFragment` content is displayed below it.
